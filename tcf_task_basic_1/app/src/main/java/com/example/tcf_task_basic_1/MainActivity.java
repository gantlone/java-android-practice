package com.example.tcf_task_basic_1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        String[] items = {"羅成員1","羅成員2","羅成員3","羅成員4","羅成員5","羅成員6"};
        boolean[] bs = {false,false,false,false,false,false};
        Button btn = findViewById(R.id.button);
        TextView tv = findViewById(R.id.output);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Resources res = getResources();
                String title = res.getString(R.string.str2);
                AlertDialog.Builder bi = new AlertDialog.Builder(MainActivity.this);
                bi.setTitle(title);
                bi.setMultiChoiceItems(items, bs, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        bs[i]=b;
                    }
                });
                bi.setNegativeButton("離開", null);
                bi.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int g) {
                        String output="";
                        for(int i=0;i<items.length;i++){
                            if(bs[i]){
                                output += items[i]+"\n";
                            }
                        }
                        tv.setText(output);
                    }
                });
                bi.show();
            }
        });
    }
}