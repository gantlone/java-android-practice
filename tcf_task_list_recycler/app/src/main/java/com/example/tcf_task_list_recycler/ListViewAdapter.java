package com.example.tcf_task_list_recycler;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<student> {
    private Context context;
    private ArrayList<student> list;

    public ListViewAdapter(@NonNull Context context, int resource,  ArrayList<student> list) {
        super(context, android.R.layout.simple_list_item_1,list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.rv_lv_view, null , false);
        student s =  list.get(position);

        TextView tv_name = v.findViewById(R.id.rv_name);
        TextView tv_number = v.findViewById(R.id.rv_number);
        ImageView img = v.findViewById(R.id.imageView);

        tv_name.setText(s.getStudent_name());
        tv_number.setText(String.valueOf(s.getStudent_number()));
        img.setImageDrawable(s.getStudent_pic_num());

        return v;
    }
}
