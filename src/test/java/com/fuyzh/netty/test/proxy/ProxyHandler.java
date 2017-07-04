package com.fuyzh.netty.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by zhaoss on 2017/7/4.
 */
public class ProxyHandler implements InvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName());
        System.out.println(method.getDeclaringClass().getName());
        System.out.println(args[0]);
        return 1;
    }
}
