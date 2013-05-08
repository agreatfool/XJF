package com.xenojoshua.xjf.netty.client;

import com.xenojoshua.xjf.log.XjfLogger;
import com.xenojoshua.xjf.netty.client.handler.XjfNettyClientHandler;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.frame.DelimiterBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.Delimiters;

import java.net.InetSocketAddress;
import java.util.LinkedList;
import java.util.concurrent.Executors;

public class XjfNettyClient {

    private ClientBootstrap bootstrap;
    private Channel channel;

    private String host;
    private int port;
    private int maxMsgSize = 8192; // 8k

    private boolean connecting = false;

    private LinkedList<String> messages = new LinkedList<String>();

    public XjfNettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public XjfNettyClient(String host, int port, int maxMsgSize) {
        this.host = host;
        this.port = port;
        this.maxMsgSize = maxMsgSize;
    }

    public void run() {

        bootstrap = new ClientBootstrap(
            new NioClientSocketChannelFactory(
                Executors.newCachedThreadPool(),
                Executors.newCachedThreadPool()
            )
        );

        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("framer", new DelimiterBasedFrameDecoder(
                    maxMsgSize, Delimiters.lineDelimiter()
                ));
                pipeline.addLast("handler", new XjfNettyClientHandler());
                return pipeline;
            }
        });

        bootstrap.setOption("tcpNoDelay", true);
        bootstrap.setOption("keepAlive", true);

        this.connect();
    }

    private void connect() {
        XjfLogger.get().debug(String.format("[xjf-netty-client] Connect to > %s:%s", host, port));
        ChannelFuture future = bootstrap.connect(new InetSocketAddress(host, port));
        connecting = true;
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                connecting = false;
                if (!channelFuture.isSuccess()) {
                    XjfLogger.get().error(
                            "[xjf-netty-client] Connection failed: " +
                                    ExceptionUtils.getStackTrace(channelFuture.getCause())
                    );
                    bootstrap.releaseExternalResources();
                } else {
                    XjfLogger.get().debug(String.format("[xjf-netty-client] Connected to > %s:%s", host, port));
                    channel = channelFuture.getChannel();
                    send();
                }
            }
        });
    }

    public void send(String msg) {
        messages.push(msg);
    }

    public void send() {

        if (messages.isEmpty()) {
            XjfLogger.get().debug("[xjf-netty-client] send(): Message queue is empty, skip!");
            return;
        } else if (!channel.isConnected()) {
            XjfLogger.get().debug("[xjf-netty-client] send(): Not connected to server, wait!");
            if (!connecting) {
                // means no connecting attempts, start to connect to server
                connect();
            }
            return;
        }

        // loop and write all messages
        ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
        while (!messages.isEmpty()) {
            String msg = messages.removeLast() + "\n"; // add line delimiter
            buffer.writeBytes(msg.getBytes());
        }

        ChannelFuture future = channel.write(buffer);
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (!channelFuture.isSuccess()) {
                    XjfLogger.get().error(
                            "[xjf-netty-client] Send message failed: " +
                                    ExceptionUtils.getStackTrace(channelFuture.getCause())
                    );
                    channelFuture.getChannel().close();
                    bootstrap.releaseExternalResources();
                } else {
                    XjfLogger.get().debug("[xjf-netty-client] Send message completed!");
                }
            }
        });

        send();

    }
}
