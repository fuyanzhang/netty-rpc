package com.fuyzh.netty.core;

import com.fuyzh.netty.RPCException;
import com.fuyzh.netty.proxy.ServiceProxy;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zss on 2017/7/4.
 */
public class ServiceManager {
    private static final Logger LOGGER = Logger.getLogger(ServiceManager.class);
    //启动时加载定义的所有服务，key为服务名称，value为服务接口
    private static final Map<String, Class<?>> SERVICEINFCLASS = new ConcurrentHashMap<String, Class<?>>();

    public static <T> T getService(String serviceName) throws RPCException {
        if (!SERVICEINFCLASS.containsKey(serviceName)) {
            //服务找不到
            LOGGER.error("Service["+serviceName+"] not found. plz check it!!");
            throw new RPCException("Service not found!!!");
        }
        Class<T> classInf = (Class<T>) SERVICEINFCLASS.get(serviceName);
        return (T) Proxy.newProxyInstance(classInf.getClassLoader(), new Class<?>[]{classInf}, new ServiceProxy());
    }

    /**
     * 启动时加载所有服务
     */
    public static void init() {
        //TODO
        SERVICEINFCLASS.put("Demo",Demo.class);

    }
}
