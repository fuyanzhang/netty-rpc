package com.fuyzh.netty.proxy;

import com.fuyzh.netty.domain.request.MessageRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Created by zss on 2017/7/4.
 * 利用动态代理构造rpc请求体
 */
public class ServiceProxy implements InvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MessageRequest request = new MessageRequest();
        request.setMessageId(UUID.randomUUID().toString());
        request.setMethodName(method.getName());
        request.setParams(args);
        request.setParamTypes(method.getParameterTypes());
        return null;
    }
}
