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
    private long adInterval;

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

    public void setIsCancelUpdata(boolean isCancelUpdata) {
        edit.putBoolean("isCancelUpdata", isCancelUpdata).commit();
    }
    public void setTime(int time){
        edit.putInt("time",time).commit();
    }
    public int getCourtTime(){
        return sp.getInt("time",0);
    }
    public boolean getIsCancelUpdata() {
        return sp.getBoolean("isCancelUpdata", false);
    }

    public void saveHotWords(String hotWords) {
        edit.putString("_hot_words", hotWords).commit();
    }

    public String getHotWords() {
        return sp.getString("_hot_words", "");
    }

    public boolean isFromPay() {
        return sp.getBoolean("_isPay", false);
    }

    public void setFromPay(boolean pay) {
        edit.putBoolean("_isPay", pay).commit();
    }

    public void setServerUrl(String url) {
        edit.putString("_ip", url).commit();
    }

    public String getServerUrl() {
        return sp.getString("_ip", "");
    }

    public void setAdTime(long time) {
        edit.putLong("_ad_time", time).commit();
    }

    public long getAdTime() {
        return sp.getLong("_ad_time", System.currentTimeMillis());
    }

    public void savePassWord(String password) {
        edit.putString("password", password).commit();
    }

    public String getPassword() {
        return sp.getString("password", "");
    }

    public void setStageTouchable(boolean isTouchable){
        edit.putBoolean("isCanTouch",isTouchable).commit();
    }
    public boolean getStageTouchable(){
        return sp.getBoolean("isCanTouch",true);
    }

    public void setCurrentUpdateVersion(int appVersion) {
        edit.putInt("current_update_version", appVersion).commit();
    }
    public void setChatOpen(boolean isOpen){
        edit.putBoolean("isOpen",isOpen).commit();
    }
    public boolean getChatIsOpen(){
        return sp.getBoolean("isOpen",true);
    }
    public void setNoLoginMYMatch(boolean isLogin){
        edit.putBoolean("isLogin",isLogin);
    }
    public boolean getNoLoginMyMatch(){
        return sp.getBoolean("isLogin",false);
    }
    public void saveHeaderUrl(String url) {
        edit.putString("headerUrl", url).commit();
    }

    public String getHeaderUrl() {
        return sp.getString("headerUrl", "");
    }

    public void isModifyHeader(boolean isModifyHeader) {
        edit.putBoolean("isModifyHeader", isModifyHeader).commit();

    }
    public void setChangeTeam(boolean isHome){
        edit.putBoolean("isChangeTeam",isHome).commit();

    }
    public boolean getIsChangeTeam(){
        return sp.getBoolean("isChangeTeam",true);
    }

    public boolean getModifuHeader() {
        return sp.getBoolean("isModifyHeader", false);
    }

    public void putIsCreateMatch(boolean isCreateMatch) {
        edit.putBoolean("isCreateMatch", isCreateMatch).commit();


    }

    public void putIsFirstLoad(boolean isFirstLoad) {
        edit.putBoolean("isFirstLoad", isFirstLoad).commit();
    }

    public boolean getIsFirstLoad() {
        return sp.getBoolean("isFirstLoad", false);
    }


    public void putIsMyPublish(boolean isMyPublish) {
        edit.putBoolean("isMyPublish", isMyPublish).commit();
    }

    public boolean getIsMyPublish() {
        return sp.getBoolean("isMyPublish", false);
    }

    public void putIsCircle(boolean isCircle) {
        edit.putBoolean("isCircle", isCircle).commit();


    }

    public boolean getIsCircle() {
        return sp.getBoolean("isCircle", false);
    }

    public boolean getIsCreateMatch() {
        return sp.getBoolean("isCreateMatch", false);
    }

    public int getCurrentUpdateViewsion() {
        return sp.getInt("current_update_version", 1);
    }

    public void setAdInterval(long adInterval) {
        edit.putLong("adInterval", adInterval).commit();
    }

    public long getAdInterval() {
        return sp.getLong("adInterval", 60 * 60 * 1000);
    }

    public void setShouldShowNotification(boolean show) {
        edit.putBoolean("_can_show", show).commit();
    }

    public boolean shouldShowNotification() {
        return sp.getBoolean("_can_show", false);
    }

    public void savePayResultInfo(String string) {
        edit.putString("_pay_result_info", string).commit();
    }

    public String getPayResultInfo() {
        return sp.getString("_pay_result_info", "");
    }
}
