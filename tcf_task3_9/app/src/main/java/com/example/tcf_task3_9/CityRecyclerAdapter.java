package com.example.tcf_task3_9;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CityRecyclerAdapter extends RecyclerView.Adapter<CityRecyclerAdapter.CityViewHolder> {
    private Context context;
    private List<City> list;

    private int createViewHolderCounter = 0;

    private int bindViewHolderCounter = 0;

    private OnItemClickListener onItemClickListener;
    public CityRecyclerAdapter(Context context, List<City> list) {
        this.context = context;
        this.list = list;
    }

    //RecycleView不支援OnItemClickListener，所以要有個抽象類來提供該方法
    interface OnItemClickListener{
        public void onItemClick(
            CityViewHolder holder,
                    int position,
                            City city
        );
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_view, parent, false);
        CityViewHolder holder = new CityViewHolder(v);
        createViewHolderCounter++;
        Log.d("onCreateViewHolder","createViewHolderCounter = " + createViewHolderCounter);
        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        bindViewHolderCounter++;
        Log.d("onBindViewHolder","bindViewHolderCounter = " + bindViewHolderCounter);
        City city = list.get(position);
        holder.iv.setImageResource(city.getPicId());
        holder.tv.setText(city.getName());
        if(onItemClickListener!=null){
            holder.rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder, holder.getAdapterPosition(),city);
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CityViewHolder extends  RecyclerView.ViewHolder{
        ImageView iv;
        TextView tv;
        View rowView;

        public CityViewHolder(@NonNull View itemView) {
            super(itemView);

            iv = itemView.findViewById(R.id.row_view_iv);
            tv = itemView.findViewById(R.id.row_view_tv);
            rowView = itemView;
        }
    }
}
