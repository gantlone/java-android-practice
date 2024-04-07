package com.example.tcf_task3_9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        //1
        rv = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        rv.setLayoutManager(layoutManager);
        Switch sw = findViewById(R.id.switch1);

        //2
        ArrayList<City> list = new ArrayList<>();
        for(int i=0;i<4;i++){
            list.add(new City("台北",R.drawable.hangzhou));
            list.add(new City("北投",R.drawable.ningbo));
            list.add(new City("公館路",R.drawable.wenzhou));
        }

        //3
        CityRecyclerAdapter ada = new CityRecyclerAdapter(
                this,
                list
        );
        ada.setOnItemClickListener(new CityRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CityRecyclerAdapter.CityViewHolder holder, int position, City city) {
                Toast.makeText(getApplication(), city.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                RecyclerView.LayoutManager layoutManager;
                if(b == true){
                    compoundButton.setText("水平");
                    layoutManager = new LinearLayoutManager(getApplication(), LinearLayoutManager.HORIZONTAL,false);
                }else{
                    compoundButton.setText("網格");
                    layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL);
//                   layoutManager = new GridLayoutManager(getApplication(),2,GridLayoutManager.HORIZONTAL,false);
                }
                rv.setLayoutManager(layoutManager);
            }

        });

        //4
        rv.setAdapter(ada);


    }

}