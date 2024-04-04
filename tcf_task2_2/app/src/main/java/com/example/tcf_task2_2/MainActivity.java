package com.example.tcf_task2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);
        final Button bt = findViewById(R.id.button);
        final TextView tv = findViewById(R.id.textView);
        bt.setOnClickListener(
            view ->{
                tv.setText("窩矮柔成員");
            }
        );
    }
}