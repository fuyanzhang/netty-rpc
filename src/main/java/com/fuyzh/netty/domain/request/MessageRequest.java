package com.fuyzh.netty.domain.request;

/**
 * Created by zss on 2017/7/3.
 */

import java.io.Serializable;
import java.util.Arrays;

/**
 * rpc 服务请求体
 */
public class MessageRequest implements Serializable {

    private static final long serialVersionUID = 7784547639298359821L;
    private String messageId;
    private String serviceName;
    private String methodName;
    private Class<?>[] paramTypes;
    private Object[] params;

    public MessageRequest() {
    }

    public MessageRequest(String messageId, String serviceName, String methodName, Class<?>[] paramTypes, Object[]
            params) {
        this.messageId = messageId;
        this.serviceName = serviceName;
        this.methodName = methodName;
        this.paramTypes = paramTypes;
        this.params = params;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Class<?>[] paramTypes) {
        this.paramTypes = paramTypes;
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

        sb.append("MessageRequest{");
        sb.append("messageId='");
        sb.append(messageId);
        sb.append('\'');
        sb.append(", serviceName='");
        sb.append(serviceName);
        sb.append('\'');
        sb.append(", methodName='");
        sb.append(methodName);
        sb.append('\'');
        sb.append(", paramTypes=");
        sb.append(Arrays.toString(paramTypes));
        sb.append(", params=" + Arrays.toString(params));
        sb.append('}');
        return sb.toString();
    }
}
