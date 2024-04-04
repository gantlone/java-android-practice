package com.example.tcf_task2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int count= 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        final TextView tv = findViewById(R.id.tv_result);
        Button bt = findViewById(R.id.button);
        bt.setOnClickListener(view -> {
            int i = new Random().nextInt(1000);
            tv.setText("" + i);
            String s = String.format("第%d次按按鈕",++count);
            Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();

        });
    }
}