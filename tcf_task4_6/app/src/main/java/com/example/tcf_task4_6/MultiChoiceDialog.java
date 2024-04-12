package com.example.tcf_task4_6;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class MultiChoiceDialog {
    private Context context;
    private String title;
    private String[] list;

    private boolean[] checkTable;

    public MultiChoiceDialog(Context context, String title, String[] list) {
        this.context = context;
        this.title = title;
        this.list = list;
        checkTable = new boolean[list.length];
    }

    public interface OnDialogSubmitListener{
        void onSubmit(ArrayList<String> selectedItems);
    }

    public void showDialog(OnDialogSubmitListener l){
        AlertDialog.Builder bi = new AlertDialog.Builder(context);
        bi.setTitle(title);
        boolean[] cloneChkTable = checkTable.clone();
        bi.setMultiChoiceItems(list, cloneChkTable, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                cloneChkTable[i] = b;
            }
        });
        bi.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ArrayList<String> items = getSelectItem(cloneChkTable);
                if(l!=null){
                    l.onSubmit(items);
                }
            }
        });
        bi.setNegativeButton("no",null);
        bi.show();

    }

    private ArrayList<String> getSelectItem(boolean[] finalSelect){
        ArrayList<String> temp = new ArrayList<>();
        for(int i=0;i<finalSelect.length;i++){
            if(finalSelect[i]==true){
                temp.add(list[i]);
            }
        }

        return temp;
    }

}
