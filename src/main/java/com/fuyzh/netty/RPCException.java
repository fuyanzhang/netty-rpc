package com.fuyzh.netty;

/**
 * Created by zss on 2017/7/4.
 */
public class RPCException extends Exception {
    private String errorCode;

    public RPCException(String message) {
        super(message);
    }

    public RPCException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}
