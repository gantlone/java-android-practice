package com.example.tcf_task_basic_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main2);

        //1
        TextView tv1 = findViewById(R.id.bmi);
        TextView tv2 = findViewById(R.id.content);
        Button btn = findViewById(R.id.back);

        //2
        Intent it = getIntent();
        Double m = it.getDoubleExtra("m",0);
        Double kg = it.getDoubleExtra("kg",0);
        boolean sex = it.getBooleanExtra("sex",false);
        Double bmi = (kg/(m*m));
        DecimalFormat v = new DecimalFormat("#.##");
        String chi_result="";
        if(sex==true){
            if(bmi >= 20 && bmi <= 25){
                chi_result = "體型很棒喔";
            } else if (bmi<20) {
                chi_result = "你該多吃點";
            }else{
                chi_result = "你該少吃點多運動";
            }
        }else{
            if(bmi >= 18 && bmi <= 22){
                chi_result = "體型很棒喔";
            } else if (bmi<18) {
                chi_result = "你該多吃點";
            }else{
                chi_result = "你該少吃點多運動";
            }
        }
        String num_result = "bmi結果: "+v.format(bmi);

        tv1.setText(num_result);
        tv2.setText(chi_result);

        //3
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity2.this,MainActivity.class);
                finish();
            }
        });


    }
}