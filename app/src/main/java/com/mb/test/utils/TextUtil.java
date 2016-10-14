package com.mb.test.utils;

import android.text.Editable;
import android.text.TextUtils;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by keli on 2016/10/8 0008.
 */
public class TextUtil {
    //判断是否为空字符串
    public static boolean isValidate(String content) {
        return content != null && !"".equals(content.trim());
    }

    public static boolean isValidate(CharSequence content) {
        return content != null && !"".equals(content.toString().trim());
    }

    public static boolean isValidate(String[] content) {
        return content != null && content.length > 0;
    }

    public static boolean isValidate(Collection<?> list) {
        return list != null && list.size() > 0;
    }

    public static boolean isEmpty(String str) {
        return TextUtils.isEmpty(str);
    }

    public static boolean isEmpty(Editable editableText) {
        return TextUtils.isEmpty(editableText);
    }

    public static boolean isEmpty(Object obj) {
        return obj != null ? true : false;
    }
    //判断是否是电话号码
    public static boolean isPhone(String phoneNumber){
        String expression = "^(((13[0-9]{1})|(18[0-9]{1})|(17[6-9]{1})|(15[0-9]{1}))+\\d{8})$";
        CharSequence inputStr = phoneNumber;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        boolean isValid = false;
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }


}
