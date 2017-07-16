package com.fuyzh.netty.server.handler;

import com.fuyzh.netty.domain.request.MessageRequest;
import com.fuyzh.netty.domain.response.MessageResponse;
import com.fuyzh.netty.server.rpc.domain.MessageInfo;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by zhaoss on 2017/7/14.
 */
public class RpcServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        MessageRequest messageInfo = (MessageRequest) msg;
//        messageInfo.get
//        System.out.println("Server received :" + messageInfo.toString());
//        MessageInfo mi = new MessageInfo();
//        mi.setClazz("hello ,response");
//        ctx.writeAndFlush(mi);
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
