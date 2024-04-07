package com.example.tcf_task3_8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CityAdapter extends ArrayAdapter<City>{
    private Context context;
    private ArrayList<City> list;
    private int type; //switch控制切換list or grid
    public static final int TYPE_LIST_VIEW = 1;
    public static final int TYPE_GRID_VIEW = 2;
    public CityAdapter(@NonNull Context context, ArrayList<City> list, int type) {
        super(context, android.R.layout.simple_list_item_1,list);
        this.context = context;
        this.list = list;
        this.type = type;
    }

    //該方法不輸入type時默認為list view
    public CityAdapter(@NonNull Context context, ArrayList<City> list) {
        super(context, android.R.layout.simple_list_item_1,list);
        this.context = context;
        this.list = list;
        this.type = TYPE_LIST_VIEW;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //1 先創空的view
        View v = convertView;

        //2 調用子widget
        if(v==null){
            //調用畫面
            if(type==TYPE_LIST_VIEW){
                v = LayoutInflater.from(context).inflate(R.layout.row_list_view, null,false);
            }else{
                v = LayoutInflater.from(context).inflate(R.layout.row_grid_view,null,false);
            }
        }

        //3 呼叫子widget中內容，並且填入參數
        ImageView iv = v.findViewById(R.id.row_view_iv);
        TextView tv = v.findViewById(R.id.row_view_tv);
        City city = list.get(position);
        iv.setImageResource(city.getPicId());
        tv.setText(city.getName());

        //4 回傳畫面
        return v;
    }
}
