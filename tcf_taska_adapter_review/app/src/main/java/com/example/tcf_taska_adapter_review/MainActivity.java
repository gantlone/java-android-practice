package com.example.tcf_taska_adapter_review;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        TextView tv1 = findViewById(R.id.tv1);
        TextView tv2 = findViewById(R.id.tv2);
        Spinner sp1 = findViewById(R.id.sp1);
        Spinner sp2 = findViewById(R.id.sp2);
        ListView lv = findViewById(R.id.lv);
        ImageView iv = findViewById(R.id.imageView);

        String[] data = new String[]{"小","中","大"};
        Resources res2 = getResources();
        String[] data2 = res2.getStringArray(R.array.array_name);
        TypedArray colors = res2.obtainTypedArray(R.array.array_color);
        String[] data3 = new String[]{"台北","北投","公館路"};

        ArrayAdapter<String> ada = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                data
        );
        ArrayAdapter<String> ada2 = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_single_choice,
                data2
        );
        ArrayAdapter<String> ada3 = new ArrayAdapter<>(
          this,
          android.R.layout.simple_list_item_single_choice,
          data3
        );

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0:
                        tv1.setTextSize(12.0f);
                        break;
                    case 1:
                        tv1.setTextSize(16.0f);
                        break;
                    case 2:
                        tv1.setTextSize(20.0f);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tv2.setTextColor(colors.getColor(i, Color.BLACK));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch(i){
                    case 0:
                        iv.setImageResource(R.drawable.hangzhou);
                        break;
                    case 1:
                        iv.setImageResource(R.drawable.wenzhou);
                        break;
                    case 2:
                        iv.setImageResource(R.drawable.ningbo);
                        break;
                }
            }
        });

        sp1.setAdapter(ada);
        sp2.setAdapter(ada2);
        lv.setAdapter(ada3);

    }
}