package com.example.tcf_task6_2;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<Phone> {
    private Context context;
    private ArrayList<Phone> list;

    private PhoneDatabase db;

    public ListViewAdapter(@NonNull Context context,ArrayList<Phone> list,PhoneDatabase db) {
        super(context, android.R.layout.simple_list_item_1,list);
        this.context = context;
        this.list = list;
        this.db = db;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.d("123","222");
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.row_view, null, false);
        TextView tv_name = v.findViewById(R.id.row_view_tv_name);
        TextView tv_phone = v.findViewById(R.id.row_view_tv_phone);
        Phone phone = list.get(position);
        tv_name.setText(phone.getPhone_name());
        tv_phone.setText(phone.getPhone_number());

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu pm = new PopupMenu(context, v);
                pm.getMenuInflater().inflate(R.menu.pop_menu, pm.getMenu());
                pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.item_upd:
                                alertDialog(phone, menuItem);
                                break;
                            case R.id.item_del:
                                db.delData(phone);
                                resetList();
                                break;
                        }
                        return true;
                    }
                });
                pm.show();
            }
        });

        Log.d("123",tv_name.getText().toString());
        return v;
    }

    private void alertDialog(Phone phone, MenuItem menuItem){
        AlertDialog.Builder bi = new AlertDialog.Builder(getContext());
        bi.setTitle(menuItem.getTitle());
        View v = LayoutInflater.from(context).inflate(R.layout.edit_view, null, false);
        EditText et_name = v.findViewById(R.id.et_name);
        et_name.setText(phone.getPhone_name());
        EditText et_phone = v.findViewById(R.id.et_phone);
        et_phone.setText(phone.getPhone_number());

        bi.setView(v);
        bi.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                phone.setPhone_name(et_name.getText().toString());
                phone.setPhone_number(et_phone.getText().toString());
                db.updData(phone);
                resetList();
            }
        });
        bi.setNegativeButton("取消",null);
        bi.show();
    }

    public void resetList(){
        Cursor c = db.queryAll();
        ArrayList<Phone> updList = new ArrayList<>();

        while(c.moveToNext()){
            int c_id = c.getColumnIndex("_id");
            int c_name = c.getColumnIndex("name");
            int c_phone = c.getColumnIndex("phone");
            Phone phone = new Phone(c.getInt(c_id), c.getString(c_name), c.getString(c_phone));
            updList.add(phone);
        }

        list.clear();
        list.addAll(updList);
        notifyDataSetChanged();
    }
}
