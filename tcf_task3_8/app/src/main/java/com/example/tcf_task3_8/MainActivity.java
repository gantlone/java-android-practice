package com.example.tcf_task3_8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int type = CityAdapter.TYPE_LIST_VIEW;
    LinearLayout container;
    ArrayList<City> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        //1
        Switch sw = findViewById(R.id.switch1);
        list = new ArrayList<>();
        list.add(new City("台北", R.drawable.hangzhou));
        list.add(new City("北投", R.drawable.hangzhou));
        list.add(new City("公館路", R.drawable.hangzhou));
        list.add(new City("台北", R.drawable.ningbo));
        list.add(new City("北投", R.drawable.ningbo));
        list.add(new City("公館路", R.drawable.ningbo));
        list.add(new City("台北", R.drawable.wenzhou));
        list.add(new City("北投", R.drawable.wenzhou));
        list.add(new City("公館路", R.drawable.wenzhou));

        //2 先更新this中container畫面
        container = findViewById(R.id.container);
        updateView();

        //3
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    type = CityAdapter.TYPE_LIST_VIEW;
                    compoundButton.setText("列表");
                }else{
                    type = CityAdapter.TYPE_GRID_VIEW;
                    compoundButton.setText("視圖");
                }

                updateView();
            }
        });

    }

    //動態更新container()
    private void updateView() {
        //1 確認要更新的widget
        int layout;
        if(type == CityAdapter.TYPE_LIST_VIEW) {
            layout = R.layout.list_view;
        }else{
            layout = R.layout.grid_view;
        }

        //2 清空當前widget
        container.removeAllViews();

        //3 填充內容
        View v = LayoutInflater.from(this).inflate(layout,null,false);
        CityAdapter ada = new CityAdapter(
                this,
                list,
                type
        );
        AdapterView<CityAdapter> adapterView = v.findViewById(R.id.adapterView);

        //4
        adapterView.setAdapter(ada);
        container.addView(v);

    }
}