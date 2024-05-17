package com.example.slot4;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private MainBaseAdapter adapter;
    private List<Student> list = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.mainlayout_item_view);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        listView = findViewById(R.id.listView);
        //add item
        list.add(new Student("Nguyen Van A","18",R.drawable.android));
        list.add(new Student("Nguyen Van B","19",R.drawable.android));
        list.add(new Student("Nguyen Van C","20",R.drawable.android));
        list.add(new Student("Nguyen Van D","21",R.drawable.android));
        list.add(new Student("Nguyen Van E","22",R.drawable.android));
        list.add(new Student("Nguyen Van F","23",R.drawable.android));
        list.add(new Student("Nguyen Van G","24",R.drawable.android));
        adapter = new MainBaseAdapter(this, list) ;
        listView.setAdapter(adapter);
    }


}