package com.example.tcf_task4_7_2;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PhoneDataRecyclerAdapter extends RecyclerView.Adapter<PhoneDataRecyclerAdapter.ViewHolder> {
    private Context context;
    private ArrayList<PhoneData> list;
    private OnSetItemClickListener onSetItemClickListener;

    public PhoneDataRecyclerAdapter(Context context, ArrayList<PhoneData> list) {
        this.context = context;
        this.list = list;
    }

    interface OnSetItemClickListener{
        void onItemClick(View view,int position, PhoneData item);
    }

    public void setOnItemClickListener(OnSetItemClickListener l){
        this.onSetItemClickListener = l;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name;
        TextView tv_phone;
        CheckBox cb;
        LinearLayout lt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.row_view_tv_name);
            tv_phone = itemView.findViewById(R.id.row_view_tv_phone);
            cb = itemView.findViewById(R.id.row_view_cb);
            lt = itemView.findViewById(R.id.row_view_layout);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v=LayoutInflater.from(context).inflate(R.layout.row_view, parent, false);
        ViewHolder vh;
        vh = new ViewHolder(v);
        vh.setIsRecyclable(false); //禁止重複生成view holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PhoneData pd = list.get(position);
        holder.tv_phone.setText(pd.getPhone());
        holder.tv_name.setText(pd.getName());
        holder.cb.setChecked(pd.isChecked());
        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                pd.setChecked(b);
            }
        });
        if(onSetItemClickListener!=null){
            holder.lt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSetItemClickListener.onItemClick(view, holder.getAdapterPosition(), pd);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void selectAll(){
        for (PhoneData pd:list) {
            pd.setChecked(true);
        }
        notifyDataSetChanged();
    }

    public void selectAllClear(){
        for (PhoneData pd:list) {
            pd.setChecked(false);
        }
        notifyDataSetChanged();
    }

    public void selectReverse(){
        for (PhoneData pd:list) {
            pd.setChecked(!pd.isChecked());
        }
        notifyDataSetChanged();
    }

    public ArrayList<PhoneData> getChkCountList(){
        return list;
    }
    public int getChkCount(){
        int count = 0;
        for (PhoneData pd:list) {
            if(pd.isChecked()){
                count++;
            }
        }
        return count;
    }
}
