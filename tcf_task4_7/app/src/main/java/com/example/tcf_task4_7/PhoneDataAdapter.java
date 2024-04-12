package com.example.tcf_task4_7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PhoneDataAdapter extends ArrayAdapter<PhoneData> {
    private Context context;
    private ArrayList<PhoneData> list;

    public PhoneDataAdapter(@NonNull Context context, ArrayList<PhoneData> list) {
        super(context, android.R.layout.simple_list_item_1, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v;
        v= LayoutInflater.from(context).inflate(R.layout.row_view,parent,false);
        TextView tv_name = v.findViewById(R.id.row_view_tv_name);
        TextView tv_phone = v.findViewById(R.id.row_view_tv_phone);
        CheckBox cb = v.findViewById(R.id.row_view_cb);

        PhoneData pd = list.get(position);
        tv_name.setText(pd.getName());
        tv_phone.setText(pd.getPhone());
        cb.setChecked(pd.isChecked());
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                pd.setChecked(b);
            }
        });

        return v;
    }

    public void selectAll(){
//        for(int i=0;i<list.stream().count();i++){
//            list.get(i).setChecked(true);
//        }
        //for-each寫法
        for(PhoneData pd:list){
            pd.setChecked(true);
        }
        notifyDataSetChanged();
    }

    public void selectAllClear(){
        for (PhoneData pd: list){
            pd.setChecked(false);
        }
        notifyDataSetChanged();
    }

    public void selectReverse(){
        for (PhoneData phoneData : list) {
            phoneData.setChecked(!phoneData.isChecked());
        }
        notifyDataSetChanged();
    }

    public int selectCount(){
        int count = 0;
        for (PhoneData pd: list) {
            if(pd.isChecked()){
                count++;
            }
        }
        return count;
    }

    public ArrayList<PhoneData> selectReturnList(){
        return list;
    }

    
}
