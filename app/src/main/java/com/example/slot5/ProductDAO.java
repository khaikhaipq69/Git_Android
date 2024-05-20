package com.example.slot5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.*;
import java.util.List;

public class ProductDAO {
    private Context context;
    private SQLiteHelper dbHelper;
    private SQLiteDatabase db;
    public ProductDAO(Context context) {
        this.context = context;
        dbHelper = new SQLiteHelper(context);
        db = dbHelper.getWritableDatabase();

    }

    //insert

    public int insertProduct(Product p){
        ContentValues values = new ContentValues();//data for insert
        //put data
        values.put("id", p.getId());
        values.put("name", p.getName());
        values.put("price", p.getPrice());
        //insert
        if(db.insert("product", null, values) <0){
            return -1;
        }
        return 1;
    }
    //get data
    public List<Product> getAllProduct(){
        List <Product> list = new ArrayList<>();
        //cursor read data
        Cursor c = db.query("product", null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false){
            Product p = new Product();
            p.setId(c.getString(0));
            p.setName(c.getString(1));
            p.setPrice(c.getDouble(2));
            list.add(p);
            c.moveToNext();

        }
        c.close();
        return list;
    }
}
