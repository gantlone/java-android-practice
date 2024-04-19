package com.example.tcf_task6_2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    PhoneDatabase db;
    ListViewAdapter ada;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        //1
        lv = findViewById(R.id.listView);
        Button bt_reset = findViewById(R.id.bt_reset);
        Button bt_insert = findViewById(R.id.bt_insert);

        //2
        db = new PhoneDatabase(this);
        db.open();
        Cursor c = db.queryAll();
        ArrayList<Phone> list = new ArrayList<>();
        while(c.moveToNext()){
            
            int value_id = c.getColumnIndex("_id");
            int value_name = c.getColumnIndex("name");
            int value_phone= c.getColumnIndex("phone");
            Phone phone = new Phone(c.getInt(value_id), c.getString(value_name), c.getString(value_phone));
            list.add(phone);
        }
        //3
        ada = new ListViewAdapter(this, list, db);
        bt_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertRandomData();
            }
        });
        bt_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.drop();
                ada.resetList();
            }
        });

        //4
        lv.setAdapter(ada);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    public void insertRandomData(){
        String s1 = "羅熊龜發泰";
        String s2 = "一二三四五";
        Random rd = new Random();
        int n1 = rd.nextInt(s1.length());
        int n2 = rd.nextInt(s2.length());
        String name = String.format("%s%s",s1.charAt(n1),s2.charAt(n2));
        String phone = String.format("%04d", rd.nextInt(10000));
        db.insertData(name,phone);
        ada.resetList();

    }
}