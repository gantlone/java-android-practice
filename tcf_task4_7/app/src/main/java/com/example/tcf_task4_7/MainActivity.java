package com.example.tcf_task4_7;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    ListView lv;
    PhoneDataAdapter ada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        //1
        tv = findViewById(R.id.tv_result);
        lv = findViewById(R.id.listView);

        //2
        ArrayList<PhoneData> list = new ArrayList<>();

        for(int i=0;i<30;i++){
            String ramPhone =  String.valueOf(new Random().nextInt(90000000)+10000000);
            list.add(new PhoneData("羅成員"+i, "09" + ramPhone));
        }
        ada = new PhoneDataAdapter(
          this,
          list
        );

        //3
        Log.d("click","set");
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PhoneData item = ada.getItem(i);
                Log.d("click",item.getName());
                tv.setText(item.getName());
            }
        });

        //4
        lv.setAdapter(ada);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opt_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.opt_send);
        String title = String.format("發送 (已勾選%2d筆)",ada.selectCount());
        item.setTitle(title);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.opt_set_all:
                ada.selectAll();
                break;
            case R.id.opt_clear_all:
            ada.selectAllClear();
            break;
             case R.id.opt_set_reverse:
            ada.selectReverse();
            break;
             case R.id.opt_send:
            ArrayList<PhoneData> list = ada.selectReturnList();
            tv.setText(list.toString());
            break;

        }

        return super.onOptionsItemSelected(item);
    }
}