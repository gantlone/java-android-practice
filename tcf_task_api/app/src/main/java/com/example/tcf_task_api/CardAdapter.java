package com.example.tcf_task_api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CardAdapter extends ArrayAdapter<Card> {
    private Context context;
    private ArrayList<Card> list;

    public CardAdapter(Context context, int resource, ArrayList<Card> list) {
        super(context, android.R.layout.simple_list_item_1, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.row_list,null,false);

        ImageView iv = v.findViewById(R.id.card_img);
        TextView tv_name = v.findViewById(R.id.card_name);
        TextView tv_atk = v.findViewById(R.id.card_atk);
        TextView tv_def = v.findViewById(R.id.card_def);

        Card card = list.get(position);

        iv.setImageBitmap(card.getImg());
        tv_name.setText(card.getName());
        tv_atk.setText(card.getAtk());
        tv_def.setText(card.getDef());

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, card.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}
