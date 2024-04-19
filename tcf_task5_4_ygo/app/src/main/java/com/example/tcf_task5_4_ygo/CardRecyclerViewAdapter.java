package com.example.tcf_task5_4_ygo;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] aa = new String[]{"123","456"};
                AlertDialog.Builder bi = new AlertDialog.Builder(context);
                bi.setNegativeButton("no", null);
                bi.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        PopupMenu pm = new PopupMenu(bi.getContext(), view);
                        pm.getMenuInflater().inflate(R.menu.opt_menu, pm.getMenu());
                        pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {
                                switch (menuItem.getItemId()){
                                    case R.id.opt_all_Chk:
                                        Float i = 0.4f;
                                        Log.d("1",i.toString());
                                        break;
                                    case R.id.opt_all_unChk:
                                        float floatNum = 3.5f;
                                        int intNum = 2;
                                        double doubleNum = 1.5;

                                        // 加法
                                        float sum1 = floatNum + intNum;
                                        double sum2 = floatNum + doubleNum;

                                        // 减法
                                        float difference1 = floatNum - intNum;
                                        double difference2 = floatNum - doubleNum;

                                        // 乘法
                                        float product1 = floatNum * intNum;
                                        double product2 = floatNum * doubleNum;

                                        // 除法
                                        float quotient1 = floatNum / intNum;
                                        double quotient2 = floatNum / doubleNum;

                                        System.out.println("加法结果： " + sum1 + ", " + sum2);
                                        System.out.println("减法结果： " + difference1 + ", " + difference2);
                                        System.out.println("乘法结果： " + product1 + ", " + product2);
                                        System.out.println("除法结果： " + quotient1 + ", " + quotient2);

                                        break;
                                }
                                AlertDialog.Builder bi2 = new AlertDialog.Builder(context);
                                String[] zz = new String[10];
                                boolean[] c = new boolean[10];
                                for(int i=0;i<10;i++){
                                    zz[i] = String.format("%s "+i,menuItem.getTitle());
                                    if((i%2)==0){
                                        c[i]=true;
                                    }
                                }
                                bi2.setMultiChoiceItems(zz, c, null);
                                bi2.show();
                                return true;
                            }
                        });
                        pm.show();
                    }
                });

                bi.setSingleChoiceItems(aa, 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, aa[i], Toast.LENGTH_SHORT).show();
                        holder.tv_card_name.setText(aa[i]);
                    }

                });
                bi.show();
            }
        });

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
