package com.fuyzh.netty.test.rpc;

import com.fuyzh.netty.client.clientservice.ServiceUtils;
import com.fuyzh.netty.test.rpc.inf.TestInf;
import com.fuyzh.netty.test.rpc.inf.domain.TestRequest;
import com.fuyzh.netty.test.rpc.inf.domain.TestResponse;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhaoss on 2017/8/6.
 */
public class TestClientMain {
    @Test
    public void test(){
        ApplicationContext atx = new ClassPathXmlApplicationContext("classpath*:META-INF/spring/testclient/*.xml");
        TestInf ti = ServiceUtils.getService(TestInf.class);
        TestRequest req = new TestRequest();
        System.out.println("开始了");
        req.setA(1);
        req.setB(2);
        System.out.println("完蛋玩意");
        TestResponse resp = ti.add(req);

        Assert.assertEquals(3,resp.getSum());
    }
    @Test
    public void test1(){
        ApplicationContext atx = new ClassPathXmlApplicationContext("classpath*:META-INF/spring/testclient/*.xml");
        TestInf ti = ServiceUtils.getService(TestInf.class);
        TestRequest req = new TestRequest();

        req.setA(2);
        req.setB(2);

        TestResponse resp = ti.add(req);

        Assert.assertNotEquals(3,resp.getSum());
    }
}
