package com.fuyzh.netty.bootstrap;

/**
 * Created by zhaoss on 2017/7/22.
 */

import com.fuyzh.netty.server.handler.RpcServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 引导器
 */
public class BootStrap {

    private static final Logger LOGGER = Logger.getLogger(BootStrap.class);

    private String serviceIp;

    private int servicePort;

    public void init() throws InterruptedException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("start to start server...");
        }
        //加载spring配置文件
        initSpring();
        //启动netty
        initNetty();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("end to start server...");
        }

    }

    //初始化spring容器
    private void initSpring() {
        ApplicationContext atx = new ClassPathXmlApplicationContext("classpath*:ApplicationContext.xml");
    }

    //启动netty
    private void initNetty() throws InterruptedException {
        final RpcServerHandler hander = new RpcServerHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        try {
            b.group(group).channel(NioServerSocketChannel.class).localAddress(servicePort).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null))).addLast(new ObjectEncoder()).addLast(hander);
                }
            });

            ChannelFuture f = b.bind().sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully().sync();
        }

    }

    public String getServiceIp() {
        return serviceIp;
    }

    public void setServiceIp(String serviceIp) {
        this.serviceIp = serviceIp;
    }

    public int getServicePort() {
        return servicePort;
    }

    public void setServicePort(int servicePort) {
        this.servicePort = servicePort;
    }
}
