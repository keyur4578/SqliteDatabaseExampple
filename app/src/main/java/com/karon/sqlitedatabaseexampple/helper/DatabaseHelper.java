package com.karon.sqlitedatabaseexampple.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE = "shopping";

    //Product Table
    public static final String PRODUCT_TABLE_NAME = "products";
    public static final String PRODUCT_ID = "product_id";
    public static final String PRODUCT_NAME = "product_name";
    public static final String PRODUCT_QTY = "product_qty";
    public static final String PRODUCT_PRICE = "product_price";
    public static final String PRODUCT_DESCRIPTION = "product_description";

    //USER TABLE

    public DatabaseHelper(Context context)
    {
       super(context,DATABASE,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String productTableQuery = "CREATE TABLE "+PRODUCT_TABLE_NAME+" ("
                +PRODUCT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +PRODUCT_NAME+" TEXT,"
                +PRODUCT_QTY+" INTEGER,"
                +PRODUCT_PRICE+" DOUBLE,"
                +PRODUCT_DESCRIPTION+" TEXT)";
        db.execSQL(productTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE  IF EXISTS "+PRODUCT_TABLE_NAME);
    }
}
