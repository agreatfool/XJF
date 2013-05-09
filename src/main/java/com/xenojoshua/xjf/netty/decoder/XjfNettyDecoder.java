package com.xenojoshua.xjf.netty.decoder;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

public class XjfNettyDecoder extends OneToOneDecoder {
    /**
     * Transforms the specified received message into another message and return
     * the transformed message.  Return {@code null} if the received message
     * is supposed to be discarded.
     */
    @Override
    protected Object decode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
