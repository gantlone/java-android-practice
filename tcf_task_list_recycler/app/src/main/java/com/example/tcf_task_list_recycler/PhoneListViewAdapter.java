package com.example.tcf_task_list_recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PhoneListViewAdapter extends ArrayAdapter<Phone> {
    private Context context;
    private ArrayList<Phone> list;

    public PhoneListViewAdapter(@NonNull Context context, int resource, ArrayList<Phone> list) {
        super(context, resource, list);
        this.context = context;
        this.list = list;
        resource = android.R.layout.simple_list_item_1;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Phone pn = list.get(position);
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.lv_sql, null, false);

        TextView tv_id = v.findViewById(R.id.tv_id);
        TextView tv_name = v.findViewById(R.id.tv_name);
        TextView tv_phone = v.findViewById(R.id.tv_phone);

        tv_id.setText(String.valueOf(pn.get_id()));
        tv_name.setText(pn.getName());
        tv_phone.setText(pn.getPhone());

        return v;
    }
}
