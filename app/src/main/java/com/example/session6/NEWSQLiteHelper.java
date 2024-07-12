package com.example.session6;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NEWSQLiteHelper extends SQLiteOpenHelper {

    public static final String SQL_CREATE_TABLE_PRODUCT = "CREATE TABLE product " +
            "(maSP text PRIMARY KEY,\n" +
            "tenSP text,\n" +
            "slSP text)";


    public NEWSQLiteHelper(Context context) {
        super(context, "QLSP", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_PRODUCT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS product");
    }
}
