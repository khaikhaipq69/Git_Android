package com.example.session6;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText txtMa, txtTen, txtSL;

    Button btnLoad, btnAdd, btnUpdate, btnDelete;

    ListView lv;
    ProductDAO productDAO;
    ArrayAdapter<String> adapter;

    List<String> ds = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        txtMa = findViewById(R.id.maSP);
        txtTen = findViewById(R.id.tenSP);
        txtSL = findViewById(R.id.slSP);
        btnLoad = findViewById(R.id.btnLoad);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        lv = findViewById(R.id.listSP);
        productDAO = new ProductDAO(this);
        ds.clear();
        ds = ProductDAO.getAllProductsToString();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ds);
        lv.setAdapter(adapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product p = new Product();
                p.setMaSP(txtMa.getText().toString());
                p.setTenSP(txtTen.getText().toString());
                p.setSlSP(Integer.parseInt(txtSL.getText().toString()));
                int result = productDAO.insertProduct(p);
                if (result == -1) {
                    Toast.makeText(MainActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
                if (result == 1) {
                    Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = productDAO.deleteProduct(txtMa.getText().toString());
                if (result == -1) {
                    Toast.makeText(MainActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
                if (result == 1) {
                    Toast.makeText(MainActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product p = new Product();
                p.setMaSP(txtMa.getText().toString());
                p.setTenSP(txtTen.getText().toString());
                p.setSlSP(Integer.parseInt(txtSL.getText().toString()));
                int result = productDAO.updateProduct(p);
                if (result == -1) {
                    Toast.makeText(MainActivity.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
                if (result == 1) {
                    Toast.makeText(MainActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ds.clear();
                ds = ProductDAO.getAllProductsToString();
                adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, ds);
                lv.setAdapter(adapter);
            }
        });
    }
}
