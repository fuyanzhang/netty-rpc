package com.fuyzh.netty.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by zhaoss on 2017/8/6.
 */
public class PropertiesLoader extends PropertyPlaceholderConfigurer {
    private static Map<String, String> propertyMap = new HashMap<String, String>();

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        for (Object key : props.keySet()
                ) {
            String keyStr = key.toString();
            propertyMap.put(keyStr, props.getProperty(keyStr));

        }

    }

    public static Object getProperty(String name) {
        return propertyMap.get(name);
    }

    public static String getString(String name) {
        return propertyMap.get(name);
    }

    public static int getInt(String name) {
        return new Integer(propertyMap.get(name)).intValue();
    }
}
