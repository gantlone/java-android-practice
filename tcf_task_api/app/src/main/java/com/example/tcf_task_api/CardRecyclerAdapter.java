package com.example.tcf_task_api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CardRecyclerAdapter extends RecyclerView.Adapter<CardRecyclerAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Card> list;
    private OnModelClickListener l;

    interface OnModelClickListener{
        void onClick(int position, Card card);
    }

    public void setOnModelClickListener(OnModelClickListener l){
        this.l = l;
    }

    public CardRecyclerAdapter(Context context, ArrayList<Card> list) {
        this.context = context;
        this.list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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
             iv = itemView.findViewById(R.id.card_img);
             v = itemView;

        }
    }

    @NonNull
    @Override
    public CardRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.row_list, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CardRecyclerAdapter.ViewHolder holder, int position) {
        Card card = list.get(position);
        holder.tv_name.setText(card.getName());
        holder.tv_atk.setText(card.getAtk());
        holder.tv_def.setText(card.getDef());
        holder.iv.setImageBitmap(card.getImg());
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                l.onClick(holder.getAdapterPosition(), card);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
