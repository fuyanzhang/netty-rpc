package com.fuyzh.netty.server.handler;

import com.fuyzh.netty.server.rpc.domain.MessageInfo;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * Created by zhaoss on 2017/6/26.
 */
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf in = (ByteBuf) msg;
//        System.out.println("Server received :" + in.toString(CharsetUtil.UTF_8));
        MessageInfo messageInfo = (MessageInfo) msg;
        System.out.println("Server received :" + messageInfo.toString());
        MessageInfo mi = new MessageInfo();
        mi.setClazz("hello ,response");
        ctx.writeAndFlush(mi);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
