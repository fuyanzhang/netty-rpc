package com.fuyzh.netty.test.rpc.inf.domain;

import java.io.Serializable;

/**
 * Created by zhaoss on 2017/7/23.
 */
public class TestResponse implements Serializable {
    private static final long serialVersionUID = 4166008570699787459L;
    private int sum;

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "TestResponse{" +
                "sum=" + sum +
                '}';
    }
}
