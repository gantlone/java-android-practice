package com.example.tcf_task_list_recycler;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class RecyclerCardAdapter extends RecyclerView.Adapter<RecyclerCardAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Card> list;

    public RecyclerCardAdapter(Context context, ArrayList<Card> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerCardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_view, parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerCardAdapter.ViewHolder holder, int position) {
        Card card = list.get(position);
        holder.tv_atk.setText(card.getAtk());
        holder.tv_def.setText(card.getDef());
        holder.tv_name.setText(card.getName());
        holder.iv.setImageBitmap(card.getImg());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name;
        TextView tv_atk;
        TextView tv_def;
        ImageView iv;
        View v;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.card_name);
            tv_atk = itemView.findViewById(R.id.card_atk);
            tv_def = itemView.findViewById(R.id.card_def);
            iv = itemView.findViewById(R.id.card_pic);
            v = itemView;
        }
    }
}
