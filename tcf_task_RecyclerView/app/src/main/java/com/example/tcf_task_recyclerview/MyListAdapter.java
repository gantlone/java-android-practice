package com.example.tcf_task_recyclerview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{
    private ArrayList<HashMap<String, String>> arrayList;
    private Context context;
    OnItemClickListener onItemClickListener;

    public MyListAdapter(Context context,ArrayList<HashMap<String, String>> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    interface OnItemClickListener{
        public void onItemClick(
            ViewHolder holder,
            int position,
            HashMap<String,String> hm
        );
    }

    public void setOnItemListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener =  onItemClickListener;
    }

    class ViewHolder extends  RecyclerView.ViewHolder{
        private TextView tvId,tvSub1,tvSub2,tvAvg;
        private View mView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.textView_id);
            tvSub1 = itemView.findViewById(R.id.textView3);
            tvSub2 = itemView.findViewById(R.id.textView5);
            tvAvg = itemView.findViewById(R.id.textView7);
            mView = itemView;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycle_item,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int intAvg = Integer.parseInt(arrayList.get(position).get("avg"));
        if(intAvg==60){
            holder.tvId.setBackgroundColor(Color.YELLOW);
        } else if (intAvg>60) {
            holder.tvId.setBackgroundColor(Color.GREEN);
        } else if (intAvg<60) {
            holder.tvId.setBackgroundColor(Color.RED);
        }
        holder.tvId.setText(arrayList.get(position).get("Id"));
        holder.tvSub1.setText(arrayList.get(position).get("Sub1"));
        holder.tvSub2.setText(arrayList.get(position).get("Sub2"));
        holder.tvAvg.setText(arrayList.get(position).get("avg"));
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(holder, holder.getAdapterPosition(), arrayList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
