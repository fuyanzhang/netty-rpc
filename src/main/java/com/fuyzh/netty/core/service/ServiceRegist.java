package com.fuyzh.netty.core.service;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhaoss on 2017/7/16.
 */
public class ServiceRegist implements ApplicationContextAware {

    private static final Map<String, Object> serviceCache = new ConcurrentHashMap<String, Object>();

    private static final Logger LOGGER = Logger.getLogger(ServiceRegist.class);

    private static ApplicationContext applicationContext;

    /**
     * 可以考虑用注解的方式来注册
     * 启动时加载，当前简单的作为spring的bean来进行注册
     * 后期用spring自定义标签，来发布注册
     */
    public static void regist(String serviceName, Object service) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("start to regist new service, the serviceName is " + serviceName);
        }

        serviceCache.put(serviceName, service);
    }

    /**
     * 暂时直接用bean id作为服务名，bean id使用接口全路径命名。
     * */
    public static Object getService(String serviceName) {
//        return serviceCache.get(serviceName);
        return applicationContext.getBean(serviceName);
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
