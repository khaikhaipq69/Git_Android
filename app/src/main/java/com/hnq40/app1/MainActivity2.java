package com.hnq40.app1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class MainActivity2 extends AppCompatActivity {
    FirebaseFirestore database;
    Button btnInsert,btnUpdate,btnDelete;
    TextView tvResult;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult=findViewById(R.id.demo151Tv1);
        database=FirebaseFirestore.getInstance();//khoi tao database
        btnInsert=findViewById(R.id.demo151BtnInsert);
        btnInsert.setOnClickListener(v->{
            insertFirebase(tvResult);
        });
        btnUpdate=findViewById(R.id.demo151BtnUpdate);
        btnUpdate.setOnClickListener(v->{
            updateFirebase(tvResult);
        });
        btnDelete=findViewById(R.id.demo151BtnDelete);
        btnDelete.setOnClickListener(v->{
            deleteFirebase(tvResult);
        });
        SelectDataFromFirebase(tvResult);
    }
    String id="";
    ToDo toDo=null;

    public void insertFirebase(TextView tvResult){
        id= UUID.randomUUID().toString();//lay 1 id bat ky
        //tao doi tuong de insert
        toDo=new ToDo(id,"title 2","content 2");
        //chuyen doi sang doi tuong co the thao tac voi firebase
        HashMap<String,Object> mapTodo=toDo.convertHashMap();
        //insert vao database
        database.collection("TODO").document(id)
                .set(mapTodo)//doi tuong can insert
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        tvResult.setText("Them thanh cong");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        tvResult.setText(e.getMessage());
                    }
                });
    }
    public void updateFirebase(TextView tvResult){
        id="c01f945e-d3a6-47c3-aa5f-e16c13515d2b";
        toDo=new ToDo(id,"sua title 1","sua content 1");
        database.collection("TODO").document(toDo.getId())
                .update(toDo.convertHashMap())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        tvResult.setText("Sua thanh cong");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        tvResult.setText(e.getMessage());
                    }
                });
    }
    public void deleteFirebase(TextView tvResult){
        id="c01f945e-d3a6-47c3-aa5f-e16c13515d2b";
        database.collection("TODO").document(id)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        tvResult.setText("Xoa thanh cong");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        tvResult.setText(e.getMessage());
                    }
                });
    }
    String strResult="";
    public ArrayList<ToDo> SelectDataFromFirebase(TextView tvResult){
        ArrayList<ToDo> list=new ArrayList<>();
        database.collection("TODO")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){//sau khi lay du lieu thanh cong
                            strResult="";
                            //doc theo tung dong du lieu
                            for(QueryDocumentSnapshot document: task.getResult()){
                                //chuyen dong doc duoc sang doi tuong
                                ToDo toDo1=document.toObject(ToDo.class);
                                //chuyen doi tuong thanhchuoi
                                strResult +="Id: "+toDo1.getId()+"\n";
                                list.add(toDo1);//them vao list
                            }
                            //hien thi ket qua
                            tvResult.setText(strResult);
                        }
                        else {
                            tvResult.setText("Doc du lieu that bai");
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        tvResult.setText(e.getMessage());
                    }
                });
        return list;
    }
}