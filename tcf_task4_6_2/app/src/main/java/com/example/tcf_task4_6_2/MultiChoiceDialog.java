package com.example.tcf_task4_6_2;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class MultiChoiceDialog {
    private Context context;
    private hobby[] hobbies;
    private String title;
    private boolean[] checkedTable;

    public MultiChoiceDialog(Context context, hobby[] hobbies, String title) {
        this.context = context;
        this.hobbies = hobbies;
        this.title = title;
        checkedTable  = new boolean[hobbies.length];
    }

    public interface OnSetSubmitListener{
        void onSubmit(ArrayList<hobby> selectedItem);
    }

    public void showDialog(OnSetSubmitListener l){
        boolean[] tempChkTable = checkedTable.clone();
        AlertDialog.Builder bi = new AlertDialog.Builder(context);

        String[] strHobbies = new String[hobbies.length];
        for (int i=0;i<hobbies.length;i++){
            strHobbies[i] = hobbies[i].getName();
            if(hobbies[i].getCount()==0){
                tempChkTable[i] = false;
            }else{
                tempChkTable[i] = true;
            }
        }
        bi.setMultiChoiceItems(
                strHobbies,
                tempChkTable,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if(b==false){
                            hobbies[i].setCount(0);
                        }else{
                            hobbies[i].setCount(1);
                        }
                        tempChkTable[i] = b;
                    }
                }
        );
        bi.setNegativeButton("no",null);
        bi.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                checkedTable = tempChkTable.clone();
                ArrayList<hobby> list = new ArrayList<>();
                for(int ii=0;ii< hobbies.length;ii++){
                    if(checkedTable[ii]==true){
                        list.add(hobbies[ii]);
                    }
                }
                l.onSubmit(list);
            }
        });
        bi.show();

    }
}
