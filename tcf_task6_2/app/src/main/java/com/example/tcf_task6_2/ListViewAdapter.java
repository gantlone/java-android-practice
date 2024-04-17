package com.example.tcf_task6_2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<Phone> {
    private Context context;
    private ArrayList<Phone> list;

    public ListViewAdapter(@NonNull Context context,ArrayList<Phone> list) {
        super(context, android.R.layout.simple_list_item_1,list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.d("123","222");
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.row_view, null, false);
        TextView tv_name = v.findViewById(R.id.row_view_tv_name);
        TextView tv_phone = v.findViewById(R.id.row_view_tv_phone);

        tv_name.setText(list.get(position).getPhone_name());
        tv_phone.setText(list.get(position).getPhone_number());
        Log.d("123",tv_name.getText().toString());
        return v;
    }
}
