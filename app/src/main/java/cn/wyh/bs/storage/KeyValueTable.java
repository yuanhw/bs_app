package cn.wyh.bs.storage;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.alibaba.fastjson.JSON;

/**
 * Created by WYH on 2018/1/18.
 */

public class KeyValueTable {

    private static final String SQL_QUERY = "select value from key_value where key = ?";
    private static final String SQL_DEL = "delete from key_value where key = ?";
    private static final String SQL_INSERT = "insert into key_value values(?, ?)";

    private static SQLiteDatabase db;

    /**
     *  通过key获取实体对象
     */
    public static <T> T getObject(SQLiteDatabase db, String key, Class<T> entity) {
        Cursor cursor = db.rawQuery(SQL_QUERY, new String[]{key});
        cursor.moveToFirst();
        String jsonStr = cursor.getString(0);
        cursor.close();
        return JSON.parseObject(jsonStr, entity);
    }

    /**
     *  获取对象
     */
    public static <T> T getObject(String key, Class<T> entity) {
        db = DBHelper.getSQLiteDatabase();
        Cursor cursor = db.rawQuery(SQL_QUERY, new String[]{key});
        if (cursor.getCount() < 1) {
            return null;
        }
        cursor.moveToFirst();
        String jsonStr = cursor.getString(0);
        cursor.close();
        return JSON.parseObject(jsonStr, entity);
    }

    /**
     *  增加对象
     */
    public static void addObject(String key, Object obj) {
        db = DBHelper.getSQLiteDatabase();
        db.execSQL(SQL_DEL, new Object[] {key});
        db.execSQL(SQL_INSERT, new Object[] {key, JSON.toJSONString(obj)});
    }

    /**
     *  移除对象通过key
     */
    public static void removeObject(String key) {
        db = DBHelper.getSQLiteDatabase();
        db.execSQL(SQL_DEL, new Object[] {key});
    }
}
