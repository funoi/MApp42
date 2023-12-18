package com.funoi.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 编写一个 SQLite 的子类,该类用于创建数据库和创建数据表 <br> 此类有一个特点:被动触发执行
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "mapp4.db"; // 数据库名称
    private static final int DB_VERSION = 1; // 数据库版本

    /**
     * 创建帮助对象以创建、打开或管理数据库 <br>
     * 在调用｛@link#getWritableDatabase｝或｛@link#getReadableDatabase｝之一之前，实际上不会创建或打开数据库 <br>
     *
     * <p>context: 当前的 Android 程序运行的环境 <br>
     * DB_name: 当前需要创建的数据库名称 <br> CursorFactory: 游标工厂,主要是用于获取 select 的执行结果,但这种方式很少使用 <br> version:
     * 当前的数据库的版本号 <br>
     *
     * @param context 用于定位数据库的路径
     */
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * 在第一次创建数据库时调用。创建表和初始填充表的地方
     *
     * @param db 数据库
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql =
                "create table student"
                        + "("
                        + "id int primary key not null,"
                        + "name varchar(20),"
                        + "age int,"
                        + "sex varchar(4),"
                        + "hobby varchar(50)"
                        + ");";
        db.execSQL(sql);
    }

    /**
     * 当数据库需要升级时调用。应该使用此方法来删除表、添加表，或者执行升级到新架构版本所需的任何其他操作。
     *
     * @param db         数据库
     * @param oldVersion 老版本
     * @param newVersion 新版本
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
