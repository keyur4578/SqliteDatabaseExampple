package com.karon.sqlitedatabaseexampple.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.karon.sqlitedatabaseexampple.classfiles.Product;

import java.util.ArrayList;

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

    public boolean insertProduct(String pname,String qty,String price,String description)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PRODUCT_NAME,pname);
        values.put(PRODUCT_QTY,qty);
        values.put(PRODUCT_PRICE,price);
        values.put(PRODUCT_DESCRIPTION,description);

        long result = db.insert(PRODUCT_TABLE_NAME,null,values);

        if(result!=-1)
        {
            return true;
        }
        else
        {
            return false;
        }

    }
    public ArrayList<Product> viewProduct()
    {
        ArrayList<Product> data = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+PRODUCT_TABLE_NAME,null);
        if(cursor.moveToFirst())
        {
            do {
                int product_id = cursor.getInt(0);
                String product_name = cursor.getString(1).toString();
                int product_qty = cursor.getInt(2);
                double product_price = cursor.getDouble(3);
                String product_description = cursor.getString(4).toString();

                Product obj = new Product();
                obj.product_id = product_id;
                obj.product_name = product_name;
                obj.product_qty = product_qty;
                obj.product_price = product_price;
                obj.product_description = product_description;

                data.add(obj);

            }while(cursor.moveToNext());
        }
        return data;

    }
    public void deleteProduct()
    {

    }
    public void updateProduct()
    {

    }


}
