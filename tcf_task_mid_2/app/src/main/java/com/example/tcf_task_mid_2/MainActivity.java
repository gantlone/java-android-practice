package com.example.tcf_task_mid_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        //1
        TextView tv = findViewById(R.id.textView);
        Button bt = findViewById(R.id.button);


        //3
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random rd = new Random();
                String[] list = new String[10];

              for(int d=0;d<10;d++){
                HashSet<Integer> hs = new HashSet<>();
                int x=0;
                for(int i=0;i<6;i++){
                    x = rd.nextInt(49)+1;
                    if(!hs.add(x)){
                        i--;
                    }
                }
                Object[] arr = hs.toArray();
                String aa="";
                for(int z=0;z<arr.length;z++){
                    aa+=arr[z] + " ";
                }
                list[d] = aa;


            }

                AlertDialog.Builder bi = new AlertDialog.Builder(MainActivity.this);
                bi.setItems(list, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tv.setText(list[i]);
                    }
                });
                bi.setTitle("Select a number set:");
                bi.show();
            };


        });

    }
}