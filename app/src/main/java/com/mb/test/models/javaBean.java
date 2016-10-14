package com.mb.test.models;

/**
 * Created by Administrator on 2016/10/8 0008.
 */

public class javaBean<T>{

    /**
     * msg : success
     * result : {"address":["黄良路","天河西路","黄村镇宋庄","黄村镇太福庄","黄村镇大庄村","北臧村镇砖楼","北臧村镇西大营","黄村镇韩园子村","北臧村镇新立村","北臧村镇六合庄"],"city":"北京市","district":"大兴区","postNumber":"102629","province":"北京市","size":"10"}
     * retCode : 200
     */

    private String msg;
    /**
     * address : ["黄良路","天河西路","黄村镇宋庄","黄村镇太福庄","黄村镇大庄村","北臧村镇砖楼","北臧村镇西大营","黄村镇韩园子村","北臧村镇新立村","北臧村镇六合庄"]
     * city : 北京市
     * district : 大兴区
     * postNumber : 102629
     * province : 北京市
     * size : 10
     */

    private int retCode;
    private T result;

    public T getData() {
        return result;
    }
    public void setData(T data){
        this.result = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }


}
