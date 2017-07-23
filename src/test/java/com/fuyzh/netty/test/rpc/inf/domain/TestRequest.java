package com.fuyzh.netty.test.rpc.inf.domain;

import java.io.Serializable;

/**
 * Created by zhaoss on 2017/7/23.
 */
public class TestRequest implements Serializable {

    private static final long serialVersionUID = -4595066632336862205L;

    private int a;
    private int b;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "TestRequest{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
