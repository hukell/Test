package com.mb.test.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
/**
 * Created by Administrator on 2016/6/7 0007.
 */
public class NetStateUtils {

    //判断当前是否有网络
    public static boolean isConnected(Context context){
        boolean ret = false;
        //判断有网络，ret=true;
        //通过系统的服务，获取ConnectivityManager
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
       // cm.getActiveNetwork() API23才能用
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();//获取到当前活跃的网络信息
        if (networkInfo != null) {
            ret=networkInfo.isConnected();
        }
        return ret;
    }
    //判断当前是否为WIFI网络
    public static boolean ifWifiConnected(Context context){
        boolean ret = false;
        //获取到ConnectivityMannager
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //获取到WIFI类型的网络
        NetworkInfo networkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (networkInfo != null) {
            ret=networkInfo.isConnected();
        }
        return ret;
    }
    //判断当前是否为手机网络
    public static boolean ifPhonegConnected(Context context){
        boolean ret = false;
        //获取到ConnectivityMannager
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //获取到手机类型的网络
        NetworkInfo networkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (networkInfo != null) {
            ret=networkInfo.isConnected();
        }
        return ret;
    }
}

