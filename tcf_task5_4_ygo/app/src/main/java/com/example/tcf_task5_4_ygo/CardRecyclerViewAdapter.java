package com.example.tcf_task5_4_ygo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class CardRecyclerViewAdapter extends RecyclerView.Adapter<CardRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Card> list;
    private OnItemClickListener l;

    public interface OnItemClickListener{
        void OnItemClick(ViewHolder vh, int position, Card card);
    }

    public void setOnItemClickListener(OnItemClickListener l){
        this.l = l;
    }

    public CardRecyclerViewAdapter(Context context, ArrayList<Card> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v ;
        v = LayoutInflater.from(context).inflate(R.layout.row_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Card card = list.get(position);
        holder.tv_card_name.setText("名稱: " + card.getCard_name());
        holder.tv_card_type.setText("類別: " + card.getCard_type());

        //圖片顯示
        Picasso.get().load(card.getCard_image()).into(holder.iv);

        //
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                l.OnItemClick(holder, holder.getAdapterPosition(), card);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_card_name;
        TextView tv_card_type;
        ImageView iv;
        View v;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_card_name = itemView.findViewById(R.id.card_name);
            tv_card_type = itemView.findViewById(R.id.card_type);
            iv = itemView.findViewById(R.id.imageView);
            v = itemView;
        }
    }
}
