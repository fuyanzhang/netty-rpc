package com.fuyzh.netty.client.clientservice;

import java.lang.reflect.Proxy;

/**
 * Created by zhaoss on 2017/8/6.
 */
public class ServiceUtils {
    public static <T> T getService(Class<T> intf) {
        return (T) Proxy.newProxyInstance(
                intf.getClassLoader(),
                new Class<?>[]{intf},
                new MessageSendProxy<T>()
        );
    }
}
