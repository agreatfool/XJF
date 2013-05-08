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

    private static final int port = 8080;

    public void run() {
        XjfLogger.get().debug("[xjf-netty-server] start...");

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
                    8192, Delimiters.lineDelimiter() // MAX size 8K(bytes)
                ));

//                pipeline.addLast("decoder", new StringDecoder());
//                pipeline.addLast("encoder", new StringEncoder());

                pipeline.addLast("handler", new XjfNettyServerHandler());
                return pipeline;
            }
        });

        // Bind and start to accept incoming connections.
        bootstrap.bind(
            new InetSocketAddress(XjfNettyServer.port)
        );

        XjfLogger.get().debug("[xjf-netty-server] started...");
    }
}
