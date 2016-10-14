package com.mb.test.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.mb.test.utils.File;
/**
 * Created by Administrator on 2015/12/30 0030.
 */

public class DBHelper extends SQLiteOpenHelper {

    //动态 回复数据表
    String sql = "create table " + File.TABLE_NAME +
            " (id integer primary key autoincrement," + "dynamicId varchar,senderId varchar,pushType varchar,content varchar,senderName varchar,senderHeader varchar,sendTime varchar, userId Integer) ";
    //应约 取消应约
    String sql_one = "create table " + File.TABLE_NAME_ONE +
            " (id integer primary key autoincrement, " + "matchId varchar, pushType varchar, content varchar, senderHeader varchar, sendTime varchar, userId Integer)";


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, File.DB_NAME, factory, File.VERSON);
    }

    public DBHelper(Context cxt) {
        this(cxt, File.DB_NAME, null, File.VERSON);//调用上面的构造方法
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
        db.execSQL(sql_one);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop if table exists " + File.TABLE_NAME);
        db.execSQL("drop if table exists " + File.TABLE_NAME_ONE);
        onCreate(db);
    }
}
