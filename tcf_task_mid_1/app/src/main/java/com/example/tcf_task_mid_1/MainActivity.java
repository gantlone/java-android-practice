package com.example.tcf_task_mid_1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        //1
        EditText et_text = findViewById(R.id.editTextTextPersonName);
        EditText et_date = findViewById(R.id.editTextDate);
        Button btn_add = findViewById(R.id.button);
        Button btn_del = findViewById(R.id.button2);
        Button btn_all = findViewById(R.id.button3);

        //2
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();

                int i=0;
                while(sp.contains("text"+i)){
                    i++;
                }
                String s = et_text.getText().toString()+"\n"+et_date.getText().toString();
                editor.putString("text"+i, et_text.getText().toString()+"\n"+et_date.getText().toString());

                editor.commit();
            }
        });

        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getSharedPreferences("data",MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                int i=0;
                while(sp.contains("text"+i)){
                    i++;
                }
                i--;
                editor.remove("text"+i);
                editor.commit();
            }
        });

        btn_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder bi = new AlertDialog.Builder(MainActivity.this);
                List<String> data = new ArrayList<>();

                SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
//                SharedPreferences.Editor editor = sp.edit();
                int i=0;
                while(sp.contains("text"+i)){
                    data.add(sp.getString("text"+i,"未存任何資料"));
                    i++;
                }

                String[] all = data.toArray(new String[0]);

                bi.setItems(all, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String select = data.get(i);
                        String[] split = select.split("\n");
                        et_text.setText(split[0]);
                        et_date.setText(split[1]);
                    }
                });

                bi.show();
            }
        });
    }
}