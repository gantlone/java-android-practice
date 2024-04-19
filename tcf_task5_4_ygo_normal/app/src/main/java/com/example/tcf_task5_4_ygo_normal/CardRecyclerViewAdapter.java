package com.example.tcf_task5_4_ygo_normal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CardRecyclerViewAdapter extends RecyclerView.Adapter<CardRecyclerViewAdapter.viewHolder> {
    private Context context;
    private ArrayList<Card> list;

    public CardRecyclerViewAdapter(Context context, ArrayList<Card> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.row_view, parent, false);
        viewHolder vh = new viewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Card card = list.get(position);

        holder.tv_name.setText(card.getCard_name());
        holder.tv_type.setText(card.getCard_type());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView tv_name;
        TextView tv_type;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.card_name);
            tv_type = itemView.findViewById(R.id.card_type);
        }
    }
}
