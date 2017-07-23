package com.fuyzh.netty.test.rpc.inf;

import com.fuyzh.netty.test.rpc.inf.domain.TestRequest;
import com.fuyzh.netty.test.rpc.inf.domain.TestResponse;

/**
 * Created by zhaoss on 2017/7/23.
 */
public interface TestInf {
     TestResponse add(TestRequest request);
}
