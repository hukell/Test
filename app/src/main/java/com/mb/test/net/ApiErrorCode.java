package com.mb.test.net;

/**
 * Created by Administrator on 2016/10/11 0011.
 * api 异常码
 */

public class ApiErrorCode {
    /** 客户端错误*/
    public static int ERROR_CLIENT_AUTHORIZED = 1;
    /** 用户授权失败*/
    public static  int ERROR_USER_AUTHORIZED = 2;
    /** 请求参数错误*/
    public static int ERROR_REQUEST_PARAM = 3;
    /** 参数检验不通过 */
    public static int ERROR_PARAM_CHECK = 4;
    /** 自定义错误*/
    public static int ERROR_OTHER = 10;
    /** 无网络连接*/
   public static int ERROR_NO_INTERNET = 11;

}
