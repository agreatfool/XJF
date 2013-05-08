package com.xenojoshua.xjf.netty.server;

import com.xenojoshua.xjf.log.XjfLogger;
import com.xenojoshua.xjf.netty.server.handler.XjfNettyServerHandler;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.frame.DelimiterBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.Delimiters;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class XjfNettyServer {

    private String host;
    private int port;
    private int maxMsgSize = 8192; // 8k

    public XjfNettyServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public XjfNettyServer(String host, int port, int maxMsgSize) {
        this.host = host;
        this.port = port;
        this.maxMsgSize = maxMsgSize;
    }

    public void run() {

        XjfLogger.get().debug("[xjf-netty-server] starting...");

        ServerBootstrap bootstrap = new ServerBootstrap(
            new NioServerSocketChannelFactory(
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

//                pipeline.addLast("decoder", new StringDecoder());
//                pipeline.addLast("encoder", new StringEncoder());

                pipeline.addLast("handler", new XjfNettyServerHandler());
                return pipeline;
            }
        });

        bootstrap.setOption("tcpNoDelay", true);
        bootstrap.setOption("keepAlive", true);

        bootstrap.bind(new InetSocketAddress(host, port));

        XjfLogger.get().debug(String.format("[xjf-netty-server] listening... > %s:%s", host, port));
    }
}
