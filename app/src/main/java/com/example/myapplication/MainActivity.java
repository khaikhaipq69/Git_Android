package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import android.Manifest;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Context context = this;

    EditText txt1;

    TextView tvResult;

    Button btnRead, btnSave;
    //---

    EditText txtU, txtP;
    Button btnLogin, btnCancel;

    CheckBox chkSave;


    @SuppressLint("MisssingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        txtU=findViewById(R.id.loginTxtU);
        txtP=findViewById(R.id.loginTxtP);
        btnLogin=findViewById(R.id.btnLog);
        btnCancel=findViewById(R.id.btnCancel);
        chkSave=findViewById(R.id.loginCheck);
        restorePass();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });


//        txt1 = findViewById(R.id.txtInput);
//        tvResult = findViewById(R.id.txtResult);
//        btnRead = findViewById(R.id.btnRead);
//        btnSave = findViewById(R.id.btnSave);
//        requestPermission();
//        btnSave.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                saveData(txt1.getText().toString(), context);
//            }
//        });
//
//        btnRead.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String data = readData(context);
//                tvResult.setText(data);
//        }
//    });


    }

    String strU, strP;
    public void login() {
        strU = txtU.getText().toString();
        strP = txtP.getText().toString();
if (strU.isEmpty() || strP.isEmpty()){
    Toast.makeText(getApplicationContext(), "Please enter username and password", Toast.LENGTH_LONG).show();
    return;
}if(strU.equals("admin") && strP.equals("1234")){
    saveUPToPreference(strU, strP, chkSave.isChecked());
    Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_LONG).show();
        }
    }


    public void saveDataToFile(String data) {
        //B1: get path
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/data.txt";
        //B2: Create a Stream for saving
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(path));
            //B3: Write data to file
            writer.write(data);
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String saveData(String data, Context context) {
        //B1: get path
        String path = "";
        ContextWrapper wrapper = new ContextWrapper(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD_MR1) {
            path = wrapper.getExternalFilesDir(Environment.DIRECTORY_DCIM) + "/data.txt";
        } else {
            path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/data.txt";
        }        //B2: Create a Stream for saving
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(path));
            //B3: Write data to file
            writer.write(data);
            writer.close();
            return "Save data successful";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public boolean requestPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.MANAGE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.MANAGE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else {
            return true;
        }
    }

    //Android API 32
    public String readData(Context context) {
        String result = "";
        String path = "";
        ContextWrapper wrapper = new ContextWrapper(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD_MR1) {
            path = wrapper.getExternalFilesDir(Environment.DIRECTORY_DCIM) + "/data.txt";
        } else {
            path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/data.txt";
        }
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNextLine()) {
                result += scanner.nextLine() + " ";
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public void loadDataFromFile() {
        String data = "";
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/data.txt";
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNextLine()) {
                data += scanner.nextLine() + "\n";
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUPToPreference(String u, String p, boolean status) {
        SharedPreferences sharedPreferences = getSharedPreferences("H_FILE", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (!status) {
            editor.clear();
        } else {
            editor.putString("username", u);
            editor.putString("password", p);
            editor.putBoolean("REMEMBER", status);

        }
        editor.commit();
    }

    public List<Object> restorePass() {
        List<Object> list = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences("H_FILE", MODE_PRIVATE);
        boolean check = sharedPreferences.getBoolean("REMEMBER", false);
        if (check) {
            String username = sharedPreferences.getString("username", "");
            txtU.setText(username);
            String password = sharedPreferences.getString("password", "");
            txtP.setText(password);
            list.add(username);
            list.add(password);
            list.add(check);
        }
        chkSave.setChecked(check);
        return list;
    }
}
