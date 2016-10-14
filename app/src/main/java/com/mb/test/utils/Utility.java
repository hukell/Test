package com.mb.test.utils;

import java.text.DecimalFormat;

/**
 * 工具类
 * Created by hanj on 14-12-30.
 */
public class Utility {
    /**
     * 格式化浮点数据，保留1位小数
     */
    public static String formatFloat(double value) {
        DecimalFormat df = new DecimalFormat("0.0");
        return df.format(value);
    }
    /**
     * 格式化浮点数据，保留1位小数
     */
    public static int formatInt(int value) {

        int b =  (int)(value/20);
        if (b==0){
            b=1;
        }
//        DecimalFormat df = new DecimalFormat("0.0");
        return b;
    }

}
