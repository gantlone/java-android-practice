package com.example.tcf_task4_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        tv = findViewById(R.id.textView);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opt_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String s="";
        switch (item.getItemId()){
            case R.id.opt_add:
                s="增加";
                break;
            case R.id.opt_delete:
                s="刪除";
                break;
            case R.id.opt_modify:
                s="修改";
                break;
        }
        tv.setText(s);
        return super.onOptionsItemSelected(item);
    }
}