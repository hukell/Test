package com.mb.test.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.text.TextUtils;

import java.util.HashMap;

/**
 * Created by cht on 2016/7/14.
 * 联系人工具类
 */
public class ContactUtil {

    /**
     * 得到手机通讯录联系人信息
     **/
    public static HashMap<String, String> getPhoneContacts(Context context) {

        HashMap<String, String> phoneMap = new HashMap<>();
        // 查询联系人电话号码

        String[] PHONES_PROJECTION = new String[]{Phone.NUMBER, Contacts.DISPLAY_NAME};
        ContentResolver resolver = context.getContentResolver();
        // 获取手机联系人
        Cursor phoneCursor = resolver.query(Phone.CONTENT_URI, PHONES_PROJECTION, null, null, null);

        if (phoneCursor != null) {
            while (phoneCursor.moveToNext()) {
                //得到手机号码
                String phoneNumber = phoneCursor.getString(0);
                String name = phoneCursor.getString(1);
                //当手机号码为空的或者为空字段 跳过当前循环
                if (!TextUtils.isEmpty(phoneNumber)) {
                    phoneNumber = phoneNumber.trim().replace(" ", "").replace("+86", "");
                    if (TextUtils.isEmpty(name)) {
                        name = "";
                    }
                    phoneMap.put(phoneNumber, name);
                }
            }
            phoneCursor.close();
        }
        return phoneMap;
    }
}
