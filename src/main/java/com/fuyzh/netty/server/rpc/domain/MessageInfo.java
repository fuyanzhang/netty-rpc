package com.fuyzh.netty.server.rpc.domain;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by zhaoss on 2017/7/2.
 */
public class MessageInfo implements Serializable{

    private static final long serialVersionUID = 1298649347962647717L;
    private String clazz;
    private String method;
    private Object[] params;

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MessageInfo{");
        sb.append("clazz=");
        sb.append(clazz);
        sb.append(", method=");
        sb.append(method);
        sb.append(", params=");
        sb.append(Arrays.toString(params));
        sb.append("}");
        return sb.toString();
    }
}
