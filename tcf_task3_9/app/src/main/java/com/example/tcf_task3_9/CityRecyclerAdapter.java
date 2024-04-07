package com.example.tcf_task3_9;

import android.content.Context;
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
    private AdapterView.OnItemClickListener onItemClickListener;
    public CityRecyclerAdapter(Context context, List<City> list) {
        this.context = context;
        this.list = list;
    }
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
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
