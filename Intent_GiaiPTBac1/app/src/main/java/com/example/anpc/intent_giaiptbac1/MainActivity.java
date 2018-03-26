package com.example.anpc.intent_giaiptbac1;

import android.content.Intent;
import android.nfc.FormatException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText edtNumA, edtNumB;
    private Button btnEqual;
    private ListView listView;
    private ArrayList<String> mData;
    private ArrayAdapter<String> mAdapter;
    int numA, numB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFormWidgets();
        addEventFormWidgets();
    }

    private void addEventFormWidgets() {
        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    numA = Integer.parseInt(edtNumA.getText()+"");
                    numB = Integer.parseInt(edtNumB.getText()+"");
                    if(numA==0) throw new MyException();
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("pass_num1", numA);
                    intent.putExtra("pass_num2", numB);
                    startActivityForResult(intent, 2003);
                }
                catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Vui lòng nhập vào số!", Toast.LENGTH_SHORT).show();
                }
                catch (MyException e){
                    Toast.makeText(getApplicationContext(),"Phương trình vô nghiệm!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2003 && resultCode==1602){
            Toast.makeText(getApplicationContext(),"Wellcome back to MainActivity! Your last edit text: a = "+numA+", b = "+numB, Toast.LENGTH_SHORT).show();
            String strName="";
            float result = data.getFloatExtra("pass_result",2);
            String str = numA + "x + " + numB + " = 0\n=>x = " + result;
            mData.add(str);
            mAdapter.notifyDataSetChanged();
            edtNumA.setText("");
            edtNumB.setText("");

        }
    }

    private void getFormWidgets(){
        edtNumA = findViewById(R.id.edtNumA);
        edtNumB = findViewById(R.id.edtNumB);
        btnEqual = findViewById(R.id.btnEqual);
        listView = findViewById(R.id.listView);

        mData = new ArrayList<>();
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mData);
        listView.setAdapter(mAdapter);
    }

}
