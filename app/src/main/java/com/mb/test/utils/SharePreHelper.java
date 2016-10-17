package com.mb.test.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by MagicBean on 2016/01/13 15:15:28
 */
public class SharePreHelper {
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;

    private SharePreHelper() {
    }

    private static SharePreHelper helper;

    public static SharePreHelper getIns() {
        if (helper == null) {
            helper = new SharePreHelper();
        }
        return helper;
    }

    public void initialize(Context context, String name) {
        if (TextUtil.isValidate(name)) {
            sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        } else {
            sp = PreferenceManager.getDefaultSharedPreferences(context);
        }
        edit = sp.edit();
    }

    /**
     * 是否打开通讯录的状态
     *
     * @param isUpDataPhone
     */
    public void setIsUpDataPhone(boolean isUpDataPhone) {
        edit.putBoolean("isUpDataPhone", isUpDataPhone).commit();
    }

    public boolean getIsUpDataPhone() {
        return sp.getBoolean("isUpDataPhone", true);
    }
    public void setConfirmFirstSuccess(boolean isSuccess){
        edit.putBoolean("isSuccess",isSuccess).commit();
    }
    public boolean getConfirmFirstSuccess(){
        return sp.getBoolean("isSuccess",false);
    }


    public void savePath(String hotWords) {
        edit.putString("path", hotWords).commit();
    }

    public String getPath() {
        return sp.getString("path", "");
    }

}
