package com.mb.test.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * 城市选择 Manager
 */
public class DBManager {
    private Context mContext;
    private static final String TABLE_NAME = "city";
    private static final String NAME = "name";
    private static final String PINYIN = "pinyin";
    private static final String CODE = "code";
    private final SQLiteDatabase db;

    public DBManager(Context context) {
        this.mContext = context;
        db = openDatabase(mContext);
    }
        //数据库存储路径
        String filePath = "data/data/com.mb.test/area.db";
        //数据库存放的文件夹 data/data/com.main.jh 下面
        String pathStr = "data/data/com.mb.test";
        SQLiteDatabase database;

        public  SQLiteDatabase openDatabase(Context context){
            System.out.println("filePath:"+filePath);
            File jhPath=new File(filePath);
            //查看数据库文件是否存在
            if(jhPath.exists()){
                Log.i("test", "存在数据库");
                //存在则直接返回打开的数据库
                return SQLiteDatabase.openOrCreateDatabase(jhPath, null);
            }else{
                //不存在先创建文件夹
                File path=new File(pathStr);
                Log.i("test", "pathStr="+path);
                if (path.mkdir()){
                    Log.i("test", "创建成功");
                }else{
                    Log.i("test", "创建失败");
                };
                try {
                    //得到数据库的输入流
                    InputStream is = mContext.getAssets().open("area.db");
                    Log.i("test", is+"");
                    //用输出流写到SDcard上面
                    FileOutputStream fos=new FileOutputStream(jhPath);
                    Log.i("test", "fos="+fos);
                    Log.i("test", "jhPath="+jhPath);
                    //创建byte数组  用于1KB写一次
                    byte[] buffer=new byte[1024];
                    int count = 0;
                    while((count = is.read(buffer))>0){
                        Log.i("test", "得到");
                        fos.write(buffer,0,count);
                    }
                    //最后关闭就可以了
                    fos.flush();
                    fos.close();
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
                //如果没有这个数据库  我们已经把他写到SD卡上了，然后在执行一次这个方法 就可以返回数据库了
                return openDatabase(context);
            }
    }
    /**
     * 读取所有城市
     * @return
     */
    public List<City> getAllCities(){
       //查询已有数据库的字段
        String sql = "select * from city WHERE code LIKE '%00' AND code not LIKE '%0000'";
        Cursor cursor = db.rawQuery(sql, null);
        List<City> result = new ArrayList<>();
        City city;
        while (cursor.moveToNext()){
            StringBuffer buffer = new StringBuffer();
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String code = cursor.getString(cursor.getColumnIndex("code"));
            String pinyin = cursor.getString(cursor.getColumnIndex("pinyin"));
            city = new City(name, pinyin,code);
            result.add(city);
        }
        cursor.close();
        db.close();
        Collections.sort(result, new CityComparator());
        return result;
    }
  /**
     * 通过名字或者拼音搜索
     * @param keyword
     * @return
     */
    public List<City> searchCity( String keyword){
     //   SQLiteDatabase db = mContext.openOrCreateDatabase(mLoadPath, mContext.MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME +" where name like \"%" + keyword
                + "%\" or pinyin like \"%" + keyword + "%\"", null);
        List<City> result = new ArrayList<>();
        City city;
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(NAME));
            String pinyin = cursor.getString(cursor.getColumnIndex(PINYIN));
            String code = cursor.getString(cursor.getColumnIndex(CODE));
            city = new City(name, pinyin,code);
            result.add(city);
        }
        cursor.close();
        db.close();
        Collections.sort(result, new CityComparator());
        return result;
    }
    /**
     * 根据名字查询编码
     */
    public String serchCode(String cityName){
     //   SQLiteDatabase db = mContext.openOrCreateDatabase(mLoadPath, mContext.MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("select * from area where name = ?", new String[]{cityName});
        String code=null;
        while (cursor.moveToNext()){
          code = cursor.getString(cursor.getColumnIndex(CODE));
        }
        cursor.close();
        db.close();
        return code;
    }
    /**
     * 根据名字查询编码
     */
    public String serchAreaCode(String areaName){
     //   SQLiteDatabase db = mContext.openOrCreateDatabase(mLoadPath, mContext.MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("select * from area where name = ?", new String[]{areaName});
        String code=null;
        while (cursor.moveToNext()){
          code = cursor.getString(cursor.getColumnIndex(CODE));
        }
        cursor.close();
        db.close();
        return code;
    }
    /**
     * a-z排序
     */
    private class CityComparator implements Comparator<City> {
        @Override
        public int compare(City lhs, City rhs) {
            String a = lhs.getPinyin().substring(0, 1);
            String b = rhs.getPinyin().substring(0, 1);
            return a.compareTo(b);
        }
    }

    /**
     * 查询所有省分
     */
   public List<Provinces> searchProvince(){
       List<Provinces> allProvinces = new ArrayList<>();
    //   SQLiteDatabase db = mContext.openOrCreateDatabase(mLoadPath, mContext.MODE_PRIVATE, null);
       String sql = "select * from area WHERE code LIKE '%0000'";
       Cursor cursor = db.rawQuery(sql, null);

       while (cursor.moveToNext()) {
           String name = cursor.getString(cursor.getColumnIndex("name"));
           String code = cursor.getString(cursor.getColumnIndex("code"));
           Provinces provinces = new Provinces(name,code);
           allProvinces.add(provinces);
       }
       return allProvinces;
   }

    /**
     * 查询指定省份下的市
     * SELECT * FROM area WHERE code LIKE '51%' AND code LIKE '%00' AND code != '510000
     */
    public List<Citys> searchCitys(String cid,String starp){
        List<Citys> allCitys = new ArrayList<>();
      //  SQLiteDatabase db = mContext.openOrCreateDatabase(mLoadPath, mContext.MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM area WHERE code LIKE ? AND code LIKE '%00' AND code != ?", new String[]{starp,cid});

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String code = cursor.getString(cursor.getColumnIndex("code"));
            Citys citys = new Citys(name,code);
            allCitys.add(citys);
        }
        return allCitys;
    }

    /**
     * 查询指定查询指定市下的区
     * SELECT * FROM area WHERE code LIKE '5101%' AND code != '510100'

     */
    public List<Areas> searchAreas(String cid,String starp){
        List<Areas> allAreas = new ArrayList<>();
      //  SQLiteDatabase db = mContext.openOrCreateDatabase(mLoadPath, mContext.MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM area WHERE code LIKE ?  AND code != ?", new String[]{starp,cid});

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String code = cursor.getString(cursor.getColumnIndex("code"));
            Areas areas = new Areas(name,code);
            allAreas.add(areas);
        }

        return allAreas;
    }

}
