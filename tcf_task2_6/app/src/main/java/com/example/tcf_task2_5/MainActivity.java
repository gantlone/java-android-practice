package com.example.tcf_task2_5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);
        Button bt = findViewById(R.id.button);
        EditText et = findViewById(R.id.editTextTextPersonName);
        TextView tv = findViewById(R.id.textView2);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText(et.getText().toString().trim());
            }
        });
    }
}