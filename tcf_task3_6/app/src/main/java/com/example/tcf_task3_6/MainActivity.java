package com.example.tcf_task3_6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //0
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        //1
        lv = findViewById(R.id.listview);

        //2
        ArrayList<City> list = new ArrayList<>();

            list.add(new City("台北","123",R.drawable.hangzhou));
            list.add(new City("北投","456",R.drawable.ningbo));
            list.add(new City("公館路",null,R.drawable.wenzhou));
        CityAdapter ca = new CityAdapter(this, list);

        //3
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), list.get(i).getName(),Toast.LENGTH_SHORT).show();
            }
        });

        //4
        lv.setAdapter(ca);
    }
}