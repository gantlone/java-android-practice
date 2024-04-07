package com.example.tcf_task3_5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    //0
    ListView lv;
    private static final String KEY_IMAGE = "key_image";
    private static final String KEY_NAME = "key_name";
    private static final String KEY_PHONE = "key_phone";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        //1
        lv=findViewById(R.id.ListView);
        int[] pics = new int[]{R.drawable.hangzhou,R.drawable.ningbo,R.drawable.wenzhou,};
        String[] cities = new String[]{"台北","北投","公館路"};
        String[] phones = new String[]{"123","456","789"};

        //2
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        for (int i=0;i<pics.length;i++){
            HashMap<String,Object> hp = new HashMap<>();
            hp.put(KEY_IMAGE, pics[i]);
            hp.put(KEY_NAME, cities[i]);
            hp.put(KEY_PHONE, phones[i]);
            list.add(hp);
        }
        String[] from = new String[]{KEY_IMAGE,KEY_NAME,KEY_PHONE};
        int[] to = new int[]{R.id.imageView,R.id.textView2,R.id.textView3};
        SimpleAdapter sa = new SimpleAdapter(
            this,
                list,
                R.layout.widget,
                from,
                to
        );

        //3
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HashMap<String, Object> hp2 = list.get(i);
                String city = (String) hp2.get(KEY_NAME);
                Toast.makeText(getApplicationContext(), city, Toast.LENGTH_SHORT).show();
            }
        });

        //4
        lv.setAdapter(sa);

    }
}