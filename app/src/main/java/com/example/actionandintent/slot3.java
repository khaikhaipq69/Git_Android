package com.example.actionandintent;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.*;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;


public class slot3 extends AppCompatActivity {
    //declare variables
    EditText username;
    EditText password;
    Button btn1;

@Override
    protected void  onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        //references
    username = findViewById(R.id.username);
    password = findViewById(R.id.password);
    btn1 = findViewById(R.id.btnLogin);
btn1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        login();
    }
});
    }

    private void login(){
    if(username.getText().toString().equals("admin")
            &&password.getText().toString().equals("12345")){
        Toast.makeText(slot3.this, "Login Successful", Toast.LENGTH_LONG).show();
    } else{
        Toast.makeText(slot3.this, "Login Failed", Toast.LENGTH_LONG).show();
    }
    }

}