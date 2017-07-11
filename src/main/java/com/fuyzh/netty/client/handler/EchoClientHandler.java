package com.fuyzh.netty.client.handler;

import com.fuyzh.netty.server.rpc.domain.MessageInfo;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * Created by zhaoss on 2017/7/2.
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setClazz("com.fuyzh.netty.test");
        messageInfo.setMethod("hello()");
        messageInfo.setParams(new Object[]{1,2,3});
       ctx.writeAndFlush(messageInfo);

    }

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        System.out.println("client received:"+o.toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }



}
