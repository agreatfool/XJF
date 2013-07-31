package com.xenojoshua.xjf.netty.client;

import com.google.protobuf.GeneratedMessage;
import com.xenojoshua.xjf.log.XjfLogger;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.LinkedList;
import java.util.concurrent.Executors;

public abstract class XjfNettyClient {

    protected ClientBootstrap bootstrap;
    protected Channel channel;

    protected String host;
    protected int port;
    protected int maxMsgSize = 8192; // 8k

    protected boolean connecting = false;

    protected LinkedList<GeneratedMessage> messages = new LinkedList<GeneratedMessage>();

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

        bootstrap.setPipelineFactory(buildPiplineFactory());

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

    public void send(GeneratedMessage message) {
        messages.push(message);
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

        ChannelFuture future = channel.write(messages.removeLast());
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

    abstract ChannelPipelineFactory buildPiplineFactory();
}
