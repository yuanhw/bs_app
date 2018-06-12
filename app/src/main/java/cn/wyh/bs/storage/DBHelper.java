package cn.wyh.bs.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *  创建数据库表类
 * Created by WYH on 2018/1/18.
 */

public class DBHelper extends SQLiteOpenHelper {

    /**
     *  key_value建表语句
     */
    private static final String KEY_VALUE_TABLE= "create table key_value (" +
            " key text primary key," +
            " value text" +
            ")";

    /**
     *  key_value_session建表语句
     */
    private static final String KEY_VALUE_SESSION_TABLE = "create table key_value_session (" +
            " key text primary key," +
            " value text" +
            ")";

    private static final String DB_NAME = "storage.db"; //数据库名称
    private static final int DEFAULT_VERSION = 1;

    private static DBHelper dbHelper;

    private DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static DBHelper instance(Context context) {
        if (dbHelper == null) {
            dbHelper = new DBHelper(context, DB_NAME, null, DEFAULT_VERSION);
        }
        return dbHelper;
    }

    public static DBHelper instance(Context context, int version) {
        if (dbHelper == null) {
            dbHelper = new DBHelper(context, DB_NAME, null, version);
        }
        return dbHelper;
    }

    public static SQLiteDatabase getSQLiteDatabase() {
        if (dbHelper == null) {
            return null;
        }
        return dbHelper.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(KEY_VALUE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
