package com.example.anpc.intent_giaiptbac1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {
    private TextView tvResult;
    private Button btnBack;
    private float result;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Toast.makeText(getApplicationContext(),"It's very easy!", Toast.LENGTH_SHORT).show();
        tvResult = findViewById(R.id.tvResult);
        btnBack = findViewById(R.id.btnBack);

        intent = getIntent();
        int a = intent.getIntExtra("pass_num1",1);
        int b = intent.getIntExtra("pass_num2",1);

        result = (float)(-b)/a;
        tvResult.setText(result+"");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("pass_result", result);
                setResult(1602,intent);
                finish();
            }
        });
    }
}
