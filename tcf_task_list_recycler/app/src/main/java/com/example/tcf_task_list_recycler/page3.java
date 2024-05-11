package com.example.tcf_task_list_recycler;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class page3 extends AppCompatActivity {
    PhoneSQL db;
    ArrayList<Phone> list = new ArrayList<>();
    PhoneListViewAdapter ada;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page3);

        //1
        ListView lv = findViewById(R.id.list_sql);
        Button btn_add = findViewById(R.id.btn_add);
        Button btn_drop = findViewById(R.id.btn_drop);


        //2
        db = new PhoneSQL(this);
        db.open();
        Cursor c = db.selectAll();

        while(c.moveToNext()){
            int index_id = c.getColumnIndex("_id");
            int index_name = c.getColumnIndex("name");
            int index_phone = c.getColumnIndex("phone");

            list.add(new Phone(c.getInt(index_id) ,c.getString(index_name),c.getString(index_phone)));
        }

        //3
         ada = new PhoneListViewAdapter(
                this, android.R.layout.simple_list_item_1, list
        );


        //4
        lv.setAdapter(ada);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Phone pn = list.get(i);
                db.del(pn);
                list.remove(i);
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long i = db.insert("羅成員","0912345678");
                list.add(new Phone(1, "羅成員", "0912345678"));
                ada.notifyDataSetChanged();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
