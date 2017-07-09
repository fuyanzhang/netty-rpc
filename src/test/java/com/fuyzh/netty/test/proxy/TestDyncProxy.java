package com.fuyzh.netty.test.proxy;

import com.fuyzh.netty.test.proxy.inf.TestProxyInf;

import java.lang.reflect.Proxy;

/**
 * Created by zhaoss on 2017/7/4.
 */
public class TestDyncProxy {
    public static void main(String[] args) {
        ProxyHandler ph = new ProxyHandler();
        TestProxyInf testProxyInf = (TestProxyInf) Proxy.newProxyInstance(TestProxyInf.class.getClassLoader(), new
                Class<?>[]{TestProxyInf.class}, ph);
        System.out.println(testProxyInf.add(1, 2));
    }
}
