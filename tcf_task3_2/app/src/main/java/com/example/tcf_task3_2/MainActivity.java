package com.example.tcf_task3_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Spinner sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);
        Resources res = getResources();
        TextView tv = findViewById(R.id.textView);
        //1
        String[] names = res.getStringArray(R.array.my_color_name);
        TypedArray values = res.obtainTypedArray(R.array.my_color_values);

        //2
        ArrayAdapter<String> ada = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,names);

        //3
        sp=findViewById(R.id.spinner);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //失敗時默認黑色
                int color = values.getColor(i, Color.BLACK);
                tv.setTextColor(color);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //4
        sp.setAdapter(ada);
    }
}