package com.example.tcf_task4_4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        tv = findViewById(R.id.textView);

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message("羅成員");
            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] arr = {"123","456","789"};
                list(arr);
            }
        });

        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] arr = {"123","456","789"};
                radio(arr);
            }
        });
    }

    private void message(String s){
        AlertDialog.Builder bi = new AlertDialog.Builder(this);
        bi.setTitle("消息提示框").setMessage(s);
        bi.setNegativeButton("離開", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        bi.show();
    }

    private void list(String[] listStr){
        AlertDialog.Builder bi = new AlertDialog.Builder(this);
        bi.setTitle("柔成員大帥哥");
        bi.setItems(listStr, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tv.setText(listStr[i]);
            }
        });
        bi.show();
    }

    private void radio(String[] items){
        final MyInteger myInteger = new MyInteger(0);
        AlertDialog.Builder bi = new AlertDialog.Builder(this);
        bi.setTitle("窩咬金童須");
        bi.setSingleChoiceItems(items, myInteger.getId(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                myInteger.setId(i);
            }
        });
        bi.setNegativeButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tv.setText(items[myInteger.getId()]);
            }
        });
        bi.show();
    }

    class MyInteger{
        private int id = 0;

        public MyInteger(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}