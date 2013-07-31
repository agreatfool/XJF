package com.xenojoshua.xjf.netty.server;

import com.xenojoshua.xjf.log.XjfLogger;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public abstract class XjfNettyServer {

    protected String host;
    protected int port;
    protected int maxMsgSize = 8192; // 8k

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

        bootstrap.setPipelineFactory(buildPiplineFactory());

        bootstrap.setOption("tcpNoDelay", true);
        bootstrap.setOption("keepAlive", true);

        bootstrap.bind(new InetSocketAddress(host, port));

        XjfLogger.get().debug(String.format("[xjf-netty-server] listening > %s:%s", host, port));
    }

    abstract ChannelPipelineFactory buildPiplineFactory();
}
