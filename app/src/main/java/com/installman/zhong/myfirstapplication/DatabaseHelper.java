package com.installman.zhong.myfirstapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by zhong on 17-2-19.
 */

public final class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "lbsinfo.db";
    public static final String TAG = "DatabaseHelper";

    public DatabaseHelper(final Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(final SQLiteDatabase db) {
        Log.v(TAG, "populating new database");
        onUpgrade(db, 0, DB_VERSION);
    }

    public void onUpgrade(final SQLiteDatabase db, int oldV, final int newV) {

        for (int version = oldV + 1; version <= newV; version++) {
            upgradeTo(db, version);
        }
    }

    private void upgradeTo(SQLiteDatabase db, int version) {
        switch (version) {
            case 1:
                createLbsTable(db);
                break;
            case 2:
                createinfoTable(db);
                break;
            default:
                throw new IllegalStateException("Don't know how to upgrade to " + version);
        }
    }

    private void createLbsTable(SQLiteDatabase db){

        String sql = "drop table if exists lbs";
        db.execSQL(sql);

        sql = "create table lbs(" +
                "_id INTEGER DEFAULT '1' NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "address TEXT NOT NULL," +
                "latitude DOUBLE NOT NULL," +
                "longitude DOUBLE NOT NULL," +
                "accur DOUBLE NOT NULL," +
                "datetime TEXT" +
                ")";
        db.execSQL(sql);
    }

    private void createinfoTable(SQLiteDatabase db){

    }
}
