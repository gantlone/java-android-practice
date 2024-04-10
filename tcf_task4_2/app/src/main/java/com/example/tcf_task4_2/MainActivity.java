package com.example.tcf_task4_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    ListView lv;
    ArrayList<String> list = new ArrayList<>();
    ArrayAdapter<String> ada;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        //1
        tv = findViewById(R.id.tv_result);
        lv = findViewById(R.id.listView);

        //2
        for(int i=0; i<30; i++){
            list.add("item " + String.format("%2d", i));
        }
        ada = new ArrayAdapter<>(
          this,
          android.R.layout.simple_list_item_1,
                list
        );

        //3
        registerForContextMenu(lv);

        //4
        lv.setAdapter(ada);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.ctx_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.ctx_insert:
                String rd = String.valueOf(new Random().nextInt(1000));
                list.add(position, rd);
                ada.notifyDataSetChanged();
                break;
            case R.id.ctx_delete:
                list.remove(position);
                ada.notifyDataSetChanged();
                break;
        }

        return super.onContextItemSelected(item);

    }



}