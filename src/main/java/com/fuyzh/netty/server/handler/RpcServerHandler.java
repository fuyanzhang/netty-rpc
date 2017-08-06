package com.fuyzh.netty.server.handler;

import com.fuyzh.netty.core.service.ServiceRegist;
import com.fuyzh.netty.domain.request.MessageRequest;
import com.fuyzh.netty.domain.response.MessageResponse;
import com.fuyzh.netty.server.rpc.domain.MessageErrorCode;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.log4j.Logger;

/**
 * Created by zhaoss on 2017/7/14.
 */
@ChannelHandler.Sharable
public class RpcServerHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = Logger.getLogger(RpcServerHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {


        MessageRequest messageInfo = (MessageRequest) msg;
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Rpc server received request,the request=" + messageInfo);
        }
        String messageId = messageInfo.getMessageId();
        //通过serviceName 获取service
        String serviceName = messageInfo.getServiceName();

        Object service = ServiceRegist.getService(serviceName);
        Object result = MethodUtils.invokeMethod(service, messageInfo.getMethodName(), messageInfo.getParams(), messageInfo.getParamTypes());
        MessageResponse response = new MessageResponse();
        response.setMessageId(messageId);
        response.setErrorcode(MessageErrorCode.SUCESS);
        response.setResult(result);
        ctx.writeAndFlush(response);
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
