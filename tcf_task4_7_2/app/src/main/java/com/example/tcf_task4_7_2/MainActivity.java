package com.example.tcf_task4_7_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    RecyclerView rv;
    PhoneDataRecyclerAdapter ada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        //1
        tv = findViewById(R.id.tv_result);
        rv = findViewById(R.id.recyclerView);
        ArrayList<PhoneData> list = new ArrayList<>();
        for(int i=0;i<30;i++){
            String ramPhone = String.valueOf(new Random().nextInt(90000000)+10000000);
            list.add(new PhoneData("柔成員"+i, "09"+ramPhone));
        }
        rv = findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));

        //2
        ada = new PhoneDataRecyclerAdapter(
                this,
                list
        );

        //3
        ada.setOnItemClickListener(new PhoneDataRecyclerAdapter.OnSetItemClickListener() {
            @Override
            public void onItemClick(View view, int position, PhoneData item) {
                tv.setText(item.getName());
            }
        });

        //4
        rv.setAdapter(ada);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opt_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        int count = ada.getChkCount();
        menu.findItem(R.id.opt_send).setTitle(String.format("發送(%d)", count));
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
                ArrayList<PhoneData> list = ada.getChkCountList();
                String send="";
                for (PhoneData pd:list) {
                    if(pd.isChecked()){
                        send+=pd.getName() + " ";
                    }
                }
                tv.setText(send);
        }
        return super.onOptionsItemSelected(item);
    }
}