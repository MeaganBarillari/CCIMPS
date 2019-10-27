package com.example.ccimp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class SQLiteDatabaseOpenHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    // database name
    public static final String DATABASE_NAME = "CCIMP.db";

    // user table
    public static final String USER_TABLE_NAME = "User";
    public static final String COL_NAME_EMAIL = "Email";
    public static final String COL_NAME_USERID = "UserID";
    public static final String COL_NAME_PASSWORD = "Password";
    public static final String COL_NAME_TYPE = "AccountType";
    public static final String COL_NAME_ADDRESS = "Address";

    // business inventory table
    public static final String BUS_INVENTORY_TABLE_NAME = "BusinessInventory";
    public static final String COL_NAME_ITEMID = "ItemID";
    public static final String COL_NAME_QUANTITY = "Quantity";
    public static final String COL_NAME_AVAILABLE_QUANTITY = "AvailableQuantity";

    // item object
    public static final String ITEM_TABLE_NAME = "Item";
    public static final String COL_NAME_ITEMNAME = "Name";
    public static final String COL_NAME_ITEMPRICE = "Price";
    public static final String COL_NAME_CUSTOMdETAIL = "Detail";

    // customer order
    public static final String CUSTOMER_ORDER_TABLE_NAME = "CustomerOrder";
    public static final String COL_NAME_ORDERID = "OrderID";
    public static final String COL_NAME_CREATE_TIME = "CreateTime";
    public static final String COL_NAME_STATUS = "OrderStatus";
    public static final String COL_NAME_TOTAL = "TotalPrice";
    public static final String COL_NAME_BUS_USERID = "UserIDBusiness";
    public static final String COL_NAME_CUS_USERID = "UserIDCustomer";

    // supplier inventory
    public static final String SUP_INVENTORY_TABLE_NAME = "SupplierInventory";
    public static final String COL_NAME_FOOD_NAME= "FoodName";
    public static final String COL_NAME_SALESPRICE = "SalesPrice";

    // order info
    public static final String ORDER_INFO_TABLE_NAME = "OrderInfo";

    // business request
    public static final String BUS_REQUEST_TABLE_NAME = "BusinessRequest";
    public static final String COL_NAME_REQUEST_DATE = "RequestDate";
    public static final String COL_NAME_NEEDBY_DATE = "NeedByDate";
    public static final String COL_NAME_SUP_USERID = "UserIDSupplier";




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

        db.execSQL("create table " + BUS_INVENTORY_TABLE_NAME + " (" +
                COL_NAME_USERID + " INTEGER PRIMARY KEY," +
                COL_NAME_ITEMID + " INTEGER," +
                COL_NAME_QUANTITY + " INTEGER," +
                COL_NAME_AVAILABLE_QUANTITY + " INTEGER)");

        db.execSQL("create table " + ITEM_TABLE_NAME + " (" +
                COL_NAME_ITEMID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME_ITEMNAME + " TEXT," +
                COL_NAME_ITEMPRICE + " INTEGER," +
                COL_NAME_USERID + " INTEGER," +
                COL_NAME_CUSTOMdETAIL + " TEXT)");

        db.execSQL("create table " + CUSTOMER_ORDER_TABLE_NAME + " (" +
                COL_NAME_ORDERID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME_CREATE_TIME + " TEXT," +
                COL_NAME_BUS_USERID + " INTEGER," +
                COL_NAME_CUS_USERID + " INTEGER," +
                COL_NAME_STATUS + " TEXT," +
                COL_NAME_TOTAL + " INTEGER)");

        db.execSQL("create table " + SUP_INVENTORY_TABLE_NAME + " (" +
                COL_NAME_FOOD_NAME + " TEXT PRIMARY KEY," +
                COL_NAME_SALESPRICE + " INTEGER," +
                COL_NAME_ITEMID + " INTEGER," +
                COL_NAME_USERID + " INTEGER)");

        db.execSQL("create table " + ORDER_INFO_TABLE_NAME + " (" +
                COL_NAME_ORDERID + " INTEGER PRIMARY KEY," +
                COL_NAME_ITEMID + " INTEGER," +
                COL_NAME_QUANTITY + " INTEGER)");

        db.execSQL("create table " + BUS_REQUEST_TABLE_NAME + " (" +
                COL_NAME_ORDERID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME_REQUEST_DATE + " TEXT," +
                COL_NAME_NEEDBY_DATE + " TEXT," +
                COL_NAME_BUS_USERID + " INTEGER," +
                COL_NAME_SUP_USERID + " INTEGER," +
                COL_NAME_TOTAL + " INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BUS_INVENTORY_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ITEM_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CUSTOMER_ORDER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SUP_INVENTORY_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ORDER_INFO_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BUS_REQUEST_TABLE_NAME);

        onCreate(db);
    }

    public void addNewUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME_EMAIL, "123@wisc.edu");
    }
}
