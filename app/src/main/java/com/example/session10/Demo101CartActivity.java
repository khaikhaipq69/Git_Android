package com.example.session10;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.session10.R;

import java.util.List;

public class Demo101CartActivity extends AppCompatActivity {
    private ListView listView;
    private Demo101CartAdapter adapter;
    Demo10CartManager cartManager;//gio hang
    List<Product91> cartItems;//san pham trong gio hang
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo101_cart);
        listView=findViewById(R.id.demo101_cartActivity_Listview);
        //----
        cartManager=Demo10CartManager.getInstance();
        cartItems=cartManager.getCartItems();
        //---
        adapter=new Demo101CartAdapter(this,cartItems);
        listView.setAdapter(adapter);

    }
}