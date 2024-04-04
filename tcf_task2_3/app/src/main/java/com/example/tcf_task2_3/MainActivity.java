package com.example.tcf_task2_3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        EditText et = findViewById(R.id.editTextPhone);
        TextView tw = findViewById(R.id.textView);
        et.addTextChangedListener(new TextWatcher(){
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // 在文本变化之前执行的操作
            Toast tt=Toast.makeText(getApplicationContext(),"要變換囉",Toast.LENGTH_SHORT);
            tt.show();
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // 在文本变化时执行的操作
            tw.setText(s); // 设置TextView的文本为EditText中的文本
            et.setText("0");
        }

        @Override
        public void afterTextChanged(Editable editable) {
            count++;
            Toast tt=Toast.makeText(getApplicationContext(),String.valueOf(count),Toast.LENGTH_SHORT);
            tt.show();
        }

       }
       );

    }
}