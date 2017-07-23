package com.fuyzh.netty.test.rpc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhaoss on 2017/7/23.
 */
public class TestMain {

    @Test
    public void test1()
    {
        //测试服务端启动
        ApplicationContext atx = new ClassPathXmlApplicationContext("classpath*:ApplicationContext.xml");
    }
}
