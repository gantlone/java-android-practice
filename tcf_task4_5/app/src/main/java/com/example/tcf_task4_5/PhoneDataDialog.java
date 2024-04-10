package com.example.tcf_task4_5;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

public class PhoneDataDialog {
    private Context context;
    private String title;
    public interface OnSubmitListener{
        void onSubmit(PhoneData updateData);
    }

    public PhoneDataDialog(Context context, String title) {
        this.context = context;
        this.title = title;
    }

    public void showDialog(PhoneData updateData, OnSubmitListener listener){
        //1 確保資料有值，nonnull
        PhoneData temp = new PhoneData("","");
        if(updateData!=null){
            temp.setPhone(updateData.getPhone());
            temp.setName(updateData.getName());
        }

        //2 ui邏輯呈現
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_view, null, false);
        EditText et_phone = v.findViewById(R.id.dialog_et_phone);
        EditText et_name = v.findViewById(R.id.dialog_et_name);
        et_phone.setText(temp.getPhone());
        et_name.setText(temp.getName());
        AlertDialog.Builder bi = new AlertDialog.Builder(
                context
        );
        bi.setTitle(title);
        bi.setView(v);
        bi.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                PhoneData pd = new PhoneData(et_name.getText().toString(),
                        et_phone.getText().toString());
                if(pd!=null){
                    listener.onSubmit(pd);
                }
            }
        });
        bi.setNegativeButton("取消", null);
        bi.show();


    }
}
