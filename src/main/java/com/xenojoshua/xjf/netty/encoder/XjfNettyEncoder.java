package com.xenojoshua.xjf.netty.encoder;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

public class XjfNettyEncoder extends OneToOneEncoder {

    /**
     * Transforms the specified message into another message and return the
     * transformed message.  Note that you can not return {@code null}, unlike
     * you can in {@link org.jboss.netty.handler.codec.oneone.OneToOneDecoder#decode(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.Channel, Object)};
     * you must return something, at least {@link org.jboss.netty.buffer.ChannelBuffers#EMPTY_BUFFER}.
     */
    @Override
    protected Object encode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
