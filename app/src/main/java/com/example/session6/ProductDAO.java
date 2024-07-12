package com.example.session6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private static SQLiteDatabase db;
    private SQLiteOpenHelper dbhelper;
    private Context context;

    public static final String TABLE_NAME = "Product";

    public ProductDAO(Context context) {
        this.context = context;
        dbhelper = new NEWSQLiteHelper(context); //Create db
        db = dbhelper.getWritableDatabase(); //Allow write data to database

    }

    public int insertProduct(Product p) {
        ContentValues values = new ContentValues();
        values.put("maSP", p.getMaSP());
        values.put("tenSP", p.getTenSP());
        values.put("slSP", String.valueOf(p.getSlSP()));
        if (db.insert(TABLE_NAME, null, values) < 0) {
            return -1; //Not successful

        }
        return 1; //Successful
    }

    public int deleteProduct(String maSP) {
        if (db.delete(TABLE_NAME, "maSP = ?", new String[]{maSP}) <= 0) {
            return -1; //Not successful
        }
        return 1; //Successful

    }

    public int updateProduct(Product p) {
        ContentValues values = new ContentValues();
        values.put("maSP", p.getMaSP());
        values.put("tenSP", p.getTenSP());
        values.put("slSP", String.valueOf(p.getSlSP()));
        if (db.update(TABLE_NAME, values, "maSP = ?", new String[]{p.getMaSP()}) < 0) {
            return -1; //Not successful
        }
        return 1; //Successful
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Product p = new Product();
            p.setMaSP(cursor.getString(0));
            p.setTenSP(cursor.getString(1));
            p.setSlSP(Integer.parseInt(cursor.getString(2)));
            products.add(p);
            cursor.moveToNext();
        }
        cursor.close();
        return products;
    }

    public static List<String> getAllProductsToString() {
        List<String> products = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Product p = new Product();
            p.setMaSP(cursor.getString(0));
            p.setTenSP(cursor.getString(1));
            p.setSlSP(Integer.parseInt(cursor.getString(2)));
            String chuoi = p.getMaSP() + " - " + p.getTenSP() + " - " + p.getSlSP();
            products.add(chuoi);
            cursor.moveToNext();
        }
        cursor.close();
        return products;
    }
}
