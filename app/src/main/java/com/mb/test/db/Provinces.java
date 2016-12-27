package com.mb.test.db;

/**
 * Created by Administrator on 2016/11/5 0005.
 */

public class Provinces {
    private String name;
    private String code;

    public Provinces() {}

    public Provinces(String name) {
        this.name = name;
    }

    public Provinces(String name,String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
