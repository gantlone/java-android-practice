package com.example.tcf_task_fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_page);

        Intent it = getIntent();
        Phone phone = (Phone) it.getSerializableExtra("phone");

        TextView tv = findViewById(R.id.textView);
        tv.setText(phone.toString());
    }
}