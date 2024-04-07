package com.example.tcf_task3_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        //1
        TypedArray ta = getResources().obtainTypedArray(R.array.image_values);
        String[] cities = getResources().getStringArray(R.array.image_names);
        lv=findViewById(R.id.ListView);
        iv=findViewById(R.id.imageView);

        //2
        ArrayAdapter<CharSequence> ada = ArrayAdapter.createFromResource(
                this,
                R.array.image_names,
                android.R.layout.simple_list_item_1
        );

        //3
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Drawable pic = ta.getDrawable(i);
                iv.setImageDrawable(pic);
            }
        });


        //4
        lv.setAdapter(ada);
    }
}