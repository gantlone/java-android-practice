package com.example.tcf_task_list_recycler;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<student> list;

    private OnItemClick l;

    public RecyclerViewAdapter(Context context, ArrayList<student> list) {
        this.context = context;
        this.list = list;
    }

    interface OnItemClick{
        void itemClick(student s, int position, View v);
    }

    public void setOnItemClickListener2(OnItemClick l){
        this.l = l;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.rv_lv_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        student student = list.get(position);
        Log.d("",list.get(position).getStudent_name());
        Log.d("",String.valueOf( list.get(position).getStudent_number()));
        Log.d("",list.get(position).getStudent_name());
        holder.name.setText(student.getStudent_name());
        holder.number.setText(String.valueOf( list.get(position).getStudent_number()));
        holder.img.setImageDrawable(student.getStudent_pic_num());
        holder.rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                l.itemClick(student, holder.getAdapterPosition(), view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView number;
        ImageView img;

        View rowView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.rv_name);
            number = itemView.findViewById(R.id.rv_number);
            img = itemView.findViewById(R.id.imageView);
            rowView = itemView;
        }
    }
}
