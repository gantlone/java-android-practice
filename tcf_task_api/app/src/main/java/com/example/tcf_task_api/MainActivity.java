package com.example.tcf_task_api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//    ListView lv;
    RecyclerView rv;
    Spinner sp;
    TextView tv;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main_rv);

        //1
        String content_url = "https://db.ygoprodeck.com/api/v7/cardinfo.php?type=Normal%20Monster&num=20&offset=0";
//        lv = findViewById(R.id.listView);
        rv = findViewById(R.id.recyclerView);
        sp = findViewById(R.id.spinner);
        tv = findViewById(R.id.textView);
        Resources res = getResources();
        String[] item_name = res.getStringArray(R.array.sp_item_name);
        TypedArray item_color = res.obtainTypedArray(R.array.sp_item_color);
        ArrayAdapter asp = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                item_name);
        sp.setAdapter(asp);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tv.setText(item_name[i]);
                tv.setTextColor(item_color.getColor(i, Color.BLACK));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //2
        final String[] body = new String[1];
        Thread body_thread = new Thread(new Runnable() {
            @Override
            public void run() {
                body[0] = api.content(content_url);
            }
        });
        body_thread.start();
        try {
            body_thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        ArrayList<Card> list;
        try {
            list = api.listContent(body[0]);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        //3
//        CardAdapter ada = new CardAdapter(this, android.R.layout.simple_list_item_1, list);
        CardRecyclerAdapter ada = new CardRecyclerAdapter(this, list);
        ada.setOnModelClickListener(new CardRecyclerAdapter.OnModelClickListener() {
            @Override
            public void onClick(int position, Card card) {
                Toast.makeText(MainActivity.this, card.getName()+" "+String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });

        //4
//        lv.setAdapter(ada);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(ada);

        Spinner sp = findViewById(R.id.spinner);
        Resources rr = getResources();
        String[] ss = rr.getStringArray(R.array.sp_item_name);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ss);
        sp.setAdapter(aa);


    }

}