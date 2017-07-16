package com.fuyzh.netty.core.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhaoss on 2017/7/16.
 */
public class ServiceRegist {
    private static final Map<String, Object> serviceCache = new ConcurrentHashMap<String, Object>();

    public static void regist(String serviceName, Object service) {
        serviceCache.put(serviceName, service);
    }

    public static Object getService(String serviceName) {
        return serviceCache.get(serviceName);
    }
}
