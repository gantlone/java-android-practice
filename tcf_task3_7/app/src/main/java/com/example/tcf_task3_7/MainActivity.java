package com.example.tcf_task3_7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gv;
    TextView tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        //1
        ArrayList list = new ArrayList();
        for(int i=0;i<100;i++){
            list.add(String.format("item%2dT",i));
        }

        //2
        ArrayAdapter ada = new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                list
        );

        //3
        gv = findViewById(R.id.gridView);
        tv2 = findViewById(R.id.textView2);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tv2.setText((CharSequence) list.get(i));
            }
        });

        //4
        gv.setAdapter(ada);
    }
}