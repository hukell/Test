package com.mb.test.utils;

import com.mb.test.models.User;

/**
 * Created by MagicBean on 2016/03/05 14:14:53
 */
public class UserManager {
    private static UserManager mUserManager;

    private boolean isRegisterToServer;

    private UserManager() {

    }

    public static UserManager getIns() {
        if (mUserManager == null) {
            synchronized (UserManager.class) {
                mUserManager = new UserManager();
            }
        }
        return mUserManager;
    }

    public void logout() {

    }

    /**
     * 清除session
     */
    /*public void clearToken() {
        SharedPreferences mPreferences = App.getInst().getSharedPreferences("UserInfo", 0);
        mPreferences.edit().clear().commit();
    }*/

    /**
     * 刷新用户数据
     */
//    public synchronized void refreshUserInfo() {
//        if (getUser() == null) return;
//        if (getUser() != null && !TextUtil.isValidate(getUser().token)) {
//            return;
//        }
//        HashMap<String, Object> params = HttpParamsHelper.createParams();
//        params.put("token", getUser().token);
//        Api.getRetrofit().refreshUserInfo(params).enqueue(new Callback<HttpResponse<User>>() {
//            @Override
//            public void onResponse(Response<HttpResponse<User>> response) {
//                if (response.isSuccess()) {
//                    if (response.body() != null) {
//                        //token 过期或者 其他社保登录
//                        if (response.body().code == 401 || response.body().code == 403) {
//                            App.getInst().toLogin();
//                        } else {
//                            User user = response.body().getDataFrist();
//                            if (user != null) {
//                                App.getInst().setUser(user);
//                                saveUserInfo(user);
//                                if (!isRegisterToServer) {
//                                    isRegisterToServer = true;
//                                    PushManager.getInstance().initialize(App.getInst().getApplicationContext());
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//
//            }
//        });
//    }

    /***
     * 自动登录
     *
     * @return
     */
   /* public synchronized void autoLogin() {
        if (getUser() == null) return;
        if (getUser() != null && !TextUtil.isValidate(getUser().token)) {
            return;
        }
        HashMap<String, Object> params = HttpParamsHelper.createParamsFWF();
        params.put("username", getUser().username);
        params.put("password", SharePreHelper.getIns().getPassword());
        Api.getRetrofit().loginFB(params).enqueue(new RequestCallback<HttpResponseFWF2<User>>() {
            @Override
            public void onSuccess(HttpResponseFWF2<User> response) {
                Logger.i("msg:" + response.toString());
                if (!response.isSuccess()) {
                    if (response.flag == 0) {
                        if (response.code == -1) {
                        }
                    }
                } else {
                    User user = response.getData();
                    UserManager.getIns().saveUserInfo(user);
                    login(user);
                    // upDeviceToken();
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }*/

    /**
     * 获取用户信息
     *
     * @param
     */


    /*public synchronized User getUser() {
        SharedPreferences mPreferences = App.getInst().getSharedPreferences("UserInfo", 0);
        String temp = mPreferences.getString("_user", "");
        byte[] base64Bytes = Base64.decode(temp, Base64.DEFAULT);
        String retStr = new String(base64Bytes);
        return JsonParser.deserializeByJson(retStr, User.class);
    }*/

    /**
     * 保存用户信息
     * @param user
     */
    public synchronized void saveUserInfo(User user) {
        /*if (user == null) return;
        SharedPreferences mPreferences = App.getInst().getSharedPreferences("UserInfo", 0);
        String json = JsonParser.serializeToJson(user);
        String temp = new String(Base64.encode(json.getBytes(), Base64.DEFAULT));
        mPreferences.edit().putString("_user", temp).commit();*/
    }

}
