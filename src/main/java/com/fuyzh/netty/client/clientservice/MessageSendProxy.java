package com.fuyzh.netty.client.clientservice;

import com.fuyzh.netty.client.handler.RpcClientHandler;
import com.fuyzh.netty.core.utils.PropertiesLoader;
import com.fuyzh.netty.domain.request.MessageRequest;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.util.UUID;

/**
 * Created by zhaoss on 2017/8/6.
 */
public class MessageSendProxy<T> implements InvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MessageRequest request = new MessageRequest();
        request.setMessageId(UUID.randomUUID().toString());
        request.setMethodName(method.getName());
        request.setServiceName(method.getDeclaringClass().getName());
        request.setParamTypes(method.getParameterTypes());
        request.setParams(args);
        //TODO
        //当前进行连接处理并获取连接时的handler，在handler里发送消息，然后等待结果，返回结果
        //连接服务端
        RpcClientHandler handler = getHandler();
        return handler.sendMsg(request);

    }

    private RpcClientHandler getHandler() throws InterruptedException {
        RpcClientHandler handler = null;
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class).remoteAddress(new InetSocketAddress(PropertiesLoader.getString("ServiceIp"), PropertiesLoader.getInt("ServicePort"))).handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(new ObjectEncoder()).addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null))).addLast("handler", new RpcClientHandler());

            }
        });
        ChannelFuture f = null;
        try {
            f = b.connect();
            //通过条件变量，等待连接完成再进行获取
            //TODO
            Thread.sleep(5000);
            if (f.isSuccess()) {
                handler = f.channel().pipeline().get(RpcClientHandler.class);
            }
//            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            group.shutdownGracefully().sync();
        }
        return handler;
    }
}
