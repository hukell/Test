package com.mb.test.net;

/**
 * Created by Administrator on 2016/10/11 0011.
 * api 异常类
 */

public class ApiException extends RuntimeException {
    private int errorCode;

    public ApiException(int code, String msg) {
        super(msg);
        this.errorCode = code;
    }

    public int getErrorCode() {
        return errorCode;
    }

}
