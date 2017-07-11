package com.fuyzh.netty.client.handler;

import com.fuyzh.netty.domain.request.MessageRequest;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Created by zhaoss on 2017/7/2.
 */
public class RpcClientHandler extends ChannelInboundHandlerAdapter implements InvocationHandler {
    private ChannelHandlerContext ctx;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //接受消息
        super.channelRead(ctx, msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MessageRequest request = new MessageRequest();
        request.setMessageId(UUID.randomUUID().toString());
        request.setMethodName(method.getName());
        request.setServiceName(method.getDeclaringClass().getName());
        request.setParamTypes(method.getParameterTypes());
        request.setParams(args);
        return sendMsg(request);
    }

    public Object sendMsg(MessageRequest request) {
        ChannelFuture future = ctx.writeAndFlush(request);
        //TODO
        return null;
    }
}
