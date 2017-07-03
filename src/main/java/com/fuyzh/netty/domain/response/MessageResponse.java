package com.fuyzh.netty.domain.response;

import java.io.Serializable;

/**
 * Created by zss on 2017/7/3.
 * rpc响应消息体
 */
public class MessageResponse implements Serializable {

    private static final long serialVersionUID = 4981770460347308226L;
    private String messageId;
    private String errorcode;
    private Object result;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MessageResponse{");
        sb.append("messageId='").append(messageId).append('\'').append(", errorcode='").append(errorcode).append
                ('\'').append(", result=").append(result).append('}');
        return sb.toString();
    }
}
