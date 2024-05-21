package com.example.tcf_task_tab_select;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerVIewCard extends RecyclerView.Adapter<recyclerVIewCard.ViewHolder> {
    private Context context;
    private ArrayList<Card> list;

    public OnSetClickListener l;

    public recyclerVIewCard(Context context, ArrayList<Card> list) {
        this.context = context;
        this.list = list;
    }

    public interface OnSetClickListener{
        void OnSetClick(Card card, int position);
    }

    public void nn(OnSetClickListener l){
        this.l = l;
    }
    @NonNull
    @Override
    public recyclerVIewCard.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.data_row, null, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerVIewCard.ViewHolder holder, int position) {
        Card card = list.get(position);
        holder.ig.setImageBitmap(card.getPic());
        holder.tv.setText(card.getName());
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                l.OnSetClick(card, holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ig;
        TextView tv;
        View v;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ig = itemView.findViewById(R.id.imageView);
            tv= itemView.findViewById(R.id.textView);
            v= itemView;
        }
    }
}
