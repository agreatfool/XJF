package com.xenojoshua.xjf.netty.client.handler;

import java.util.concurrent.atomic.AtomicLong;

import com.xenojoshua.xjf.log.XjfLogger;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.*;

public class XjfNettyClientHandler extends SimpleChannelUpstreamHandler {

    private final AtomicLong transferredBytes = new AtomicLong();

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
        int bytes = buffer.readableBytes();
        transferredBytes.addAndGet(bytes);

//        e.getChannel().write(e.getMessage());

        XjfLogger.get().debug("[xjf-netty-client] Message received, size: " + bytes + " , message: " + e.getMessage());
        XjfLogger.get().debug("[xjf-netty-client] Total received message size: " + transferredBytes.get());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        XjfLogger.get().error("[xjf-netty-client] Unexpected exception: ");
        XjfLogger.get().error(ExceptionUtils.getStackTrace(e.getCause()));
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        XjfLogger.get().debug("[xjf-netty-client] Channel disconnected!");
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        XjfLogger.get().debug("[xjf-netty-client] Channel connected!");
    }
}
