package com.example.tcf_task_api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class re extends RecyclerView.Adapter<re.ViewHolder> {
    private Context context;
    private ArrayList<Card> list;
    private setOnClick l;

    public re(android.content.Context context, ArrayList<Card> list) {
        this.context = context;
        this.list = list;
    }

    interface setOnClick{
        void OnClick(Card card, int position);
    }

    public void setOnClickListener1(setOnClick l){
        this.l = l;
    }

    @NonNull
    @Override
    public re.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.row_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull re.ViewHolder holder, int position) {
        Card card = list.get(position);

        holder.tv1.setText(card.getName());
        holder.tv2.setText(card.getAtk());
        holder.tv3.setText(card.getDef());
        holder.iv.setImageBitmap(card.getImg());
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                l.OnClick(card, holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv1;
        TextView tv2;
        TextView tv3;
        ImageView iv;
        View v;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv1 = itemView.findViewById(R.id.card_name);
            tv2 = itemView.findViewById(R.id.card_atk);
            tv3 = itemView.findViewById(R.id.card_def);
            iv = itemView.findViewById(R.id.card_img);
            v = itemView;


        }
    }
}
