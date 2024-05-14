package com.example.equation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //Set elements
    EditText IntA, IntB, IntC;
    Button button;
    TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //

        //references
        IntA = findViewById(R.id.IntA);
        IntB = findViewById(R.id.IntB);
        IntC = findViewById(R.id.IntC);
        button = findViewById(R.id.button);
        result = findViewById(R.id.result);

        //Event handling
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SolveEquation();
            }
        });
    }

    private void SolveEquation() {
        double a = Double.parseDouble(IntA.getText().toString());
        double b = Double.parseDouble(IntB.getText().toString());
        double c = Double.parseDouble(IntC.getText().toString());
        double delta = Math.pow(b, 2) - 4 * a * c;

        if (delta < 0) {
            result.setText("No solution");
            return;
        }
        if (delta == 0) {
            result.setText("x = " + String.valueOf(-b / (2 * a)));
            return;
        }
        if (delta > 0){
            result.setText("x = " + String.valueOf((-b + Math.sqrt(delta)) / (2 * a)) + "\n" + "x = " + String.valueOf((-b - Math.sqrt(delta)) / (2 * a)));
            return;
        }
    }
}