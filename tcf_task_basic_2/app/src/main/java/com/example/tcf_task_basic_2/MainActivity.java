package com.example.tcf_task_basic_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.security.interfaces.EdECKey;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        //1
        EditText m = findViewById(R.id.tall);
        EditText kg = findViewById(R.id.weight);
        RadioGroup group = findViewById(R.id.gp);
        Button btn = findViewById(R.id.button);

        //2

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, MainActivity2.class);
                it.putExtra("m", Double.valueOf(m.getText().toString()));
                it.putExtra("kg", Double.valueOf(kg.getText().toString()));

                group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch(i){
                            case R.id.boy:
                                it.putExtra("sex", true);
                                break;
                            case R.id.girl:
                                it.putExtra("sex", false);
                                break;

                        }
                    }
                });

                startActivity(it);
            }
        });




    }
}