package com.example.slot5;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.*;

public class MainActivity extends AppCompatActivity {

    ListView listview;
    Adapter adapter;

    List<Product> list = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_51);
        listview = findViewById(R.id.listView);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        ProductDAO dao = new ProductDAO(this);
        Product p = new Product("3", "San Pham 3", 3000,3);
        int kq = dao.insertProduct(p);
        list = dao.getAllProduct();
        adapter = new Adapter(list, this);
        listview.setAdapter(adapter);
//        Toast.makeText(this, "KQ: " + kq, Toast.LENGTH_SHORT).show();
    }
}