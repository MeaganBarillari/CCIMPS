package com.example.ccimp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class SQLiteDatabaseOpenHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    // database name
    public static final String DATABASE_NAME = "CCIMP.db";

    // user table
    public static final String USER_TABLE_NAME = "user";
    public static final String COL_NAME_EMAIL = "email";
    public static final String COL_NAME_USERID = "userID";
    public static final String COL_NAME_PASSWORD = "password";
    public static final String COL_NAME_TYPE = "accountType";
    public static final String COL_NAME_ADDRESS = "address";

    // business inventory table



    public SQLiteDatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + USER_TABLE_NAME + " (" +
                COL_NAME_USERID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME_EMAIL + " TEXT," +
                COL_NAME_PASSWORD + " TEXT," +
                COL_NAME_TYPE + " TEXT," +
                COL_NAME_ADDRESS + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        onCreate(db);
    }
}
