package com.fuyzh.netty.test.rpc.inf.impl;

import com.fuyzh.netty.test.rpc.inf.TestInf;
import com.fuyzh.netty.test.rpc.inf.domain.TestRequest;
import com.fuyzh.netty.test.rpc.inf.domain.TestResponse;

/**
 * Created by zhaoss on 2017/7/23.
 */
public class TestInfImpl implements TestInf {
    public TestResponse add(TestRequest request) {
        int a = request.getA();
        int b = request.getB();
        int c = a+b;
        TestResponse response = new TestResponse();
        response.setSum(c);
        return response;
    }
}
