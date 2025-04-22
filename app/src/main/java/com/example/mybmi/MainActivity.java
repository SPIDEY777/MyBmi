package com.example.mybmi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText edtWeight = findViewById(R.id.edtWeight);
        EditText edtHeightFt = findViewById(R.id.edtHeightFt);
        EditText edtHeightIn = findViewById(R.id.edtHeightIn);
        Button buttonCalc = findViewById(R.id.buttonCalc);
        TextView textViewResult = findViewById(R.id.textViewResult);
        ConstraintLayout llmain = findViewById(R.id.layoutMain);

        buttonCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Basic validation
                if (edtWeight.getText().toString().isEmpty() ||
                        edtHeightFt.getText().toString().isEmpty() ||
                        edtHeightIn.getText().toString().isEmpty()) {
                    textViewResult.setText("Please fill all fields");
                    return;
                }

                int weight = Integer.parseInt(edtWeight.getText().toString());
                int heightFt = Integer.parseInt(edtHeightFt.getText().toString());
                int heightIn = Integer.parseInt(edtHeightIn.getText().toString());

                int totalIn = heightFt * 12 + heightIn;
                double totalCm = totalIn * 2.54;
                double totalM = totalCm / 100;
                double bmi = weight / (totalM * totalM);

                if (bmi > 25) {
                    textViewResult.setText("You are Overweight!");
                    llmain.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.overWeight));
                } else if (bmi < 18) {
                    textViewResult.setText("You are Underweight!");
                    llmain.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.underWeight));
                } else {
                    textViewResult.setText("You are Healthy!");
                    llmain.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.healthy));
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layoutMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
