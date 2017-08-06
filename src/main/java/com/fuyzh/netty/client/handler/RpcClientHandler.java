package com.fuyzh.netty.client.handler;

import com.fuyzh.netty.RPCException;
import com.fuyzh.netty.domain.MessageConstant;
import com.fuyzh.netty.domain.request.MessageRequest;
import com.fuyzh.netty.domain.response.MessageResponse;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhaoss on 2017/7/2.
 */
public class RpcClientHandler extends ChannelInboundHandlerAdapter  {
    private ChannelHandlerContext ctx;
    private MessageResponse response = null;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //接受消息
        setResponse((MessageResponse) msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

//    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        MessageRequest request = new MessageRequest();
//        request.setMessageId(UUID.randomUUID().toString());
//        request.setMethodName(method.getName());
//        request.setServiceName(method.getDeclaringClass().getName());
//        request.setParamTypes(method.getParameterTypes());
//        request.setParams(args);
//        return sendMsg(request);
//    }

    //发送消息，等到服务端返回，200s内无返回，抛异常
    public Object sendMsg(MessageRequest request) throws RPCException {

        ChannelFuture future = ctx.writeAndFlush(request);
        //TODO
        if (response == null) {
            lock.lock();
            try {
                condition.await(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new RPCException("received response timeout！！！", null, MessageConstant.TIMEOUT_ERROR);
            } finally {
                lock.unlock();
            }
        }
        return response.getResult();
    }

    private void setResponse(MessageResponse response) {
        try {
            lock.lock();
            this.response = response;
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }

}
