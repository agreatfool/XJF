package com.xenojoshua.xjf.netty.server.handler;

import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicLong;

import com.xenojoshua.xjf.log.XjfLogger;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.*;

public class XjfNettyServerHandler extends SimpleChannelUpstreamHandler {

    private final AtomicLong transferredBytes = new AtomicLong();

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
        int bytes = buffer.readableBytes();
        transferredBytes.addAndGet(bytes);

        String message = buffer.toString(Charset.forName("UTF-8"));

        // e.getChannel().write(e.getMessage()); // do not echo message here, otherwise client & server will echo without stopping

        XjfLogger.get().debug("[xjf-netty-server] Message received, size: " + bytes + " , message: " + message);
        XjfLogger.get().debug("[xjf-netty-server] Total received message size: " + transferredBytes.get());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        XjfLogger.get().error("[xjf-netty-server] Unexpected exception: ");
        XjfLogger.get().error(ExceptionUtils.getStackTrace(e.getCause()));
        e.getChannel().close();
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        XjfLogger.get().debug("[xjf-netty-server] Channel disconnected!");
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        XjfLogger.get().debug("[xjf-netty-server] Channel connected!");
    }
}
