package com.example.tcf_task_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    MyListAdapter ada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1
        RecyclerView rv = findViewById(R.id.recyclerView);
//        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setLayoutManager(new GridLayoutManager(getApplication(),2));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        SwipeRefreshLayout swipeRefreshLayout;
        swipeRefreshLayout = findViewById(R.id.refreshLayout);
        swipeRefreshLayout.setOnRefreshListener(()->{
            arrayList.clear();
            upd();
            ada.notifyDataSetChanged();
            swipeRefreshLayout.setRefreshing(false);

        });

        Switch sw = findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    rv.setLayoutManager(new LinearLayoutManager(getApplication()));
                }
                else{
                    rv.setLayoutManager(new GridLayoutManager(getApplication(),2));
                }
            }
        });

        upd();

        //2
        ada = new MyListAdapter(
                this,
                arrayList
        );
        ada.setOnItemListener(new MyListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MyListAdapter.ViewHolder holder, int position, HashMap<String, String> hm) {
                Toast.makeText(getApplication(),hm.get("Id"),Toast.LENGTH_SHORT).show();
            }
        });

        //3


        //4
        rv.setAdapter(ada);

    }

    private void upd(){
        for(int i=0;i<30;i++){
            HashMap<String, String> hm = new HashMap<>();
            hm.put("Id","學號："+String.format("%02d",i));
            hm.put("Sub1",String.valueOf(new Random().nextInt(80)+20));
            hm.put("Sub2",String.valueOf(new Random().nextInt(80)+20));
            hm.put("avg",String.valueOf((Integer.parseInt(hm.get("Sub1"))+Integer.parseInt(hm.get("Sub2")))/2));
            arrayList.add(hm);
        }

    }
}
