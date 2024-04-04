package com.example.tcf_task2_9;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        EditText et1 = findViewById(R.id.et1);
        TextView tv3 =  findViewById(R.id.tv3);
        tv3.setTextColor(Color.RED);
        TextView tv4 =  findViewById(R.id.tv4);

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv2 =  findViewById(R.id.tv2);
                tv2.setText(et1.getText());
            }
        });

        int i;
        for(i=1;i<4;i++){
            RadioButton rb = findViewById(getResources().getIdentifier("rb"+i,"id",getPackageName()));
            rb.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {
                    String tt = ((RadioButton) view).getText().toString();
                    tv3.setText(tt);
                }
            });
        }

        int r;
        for(r=1;r<4;r++){
            CheckBox cb = findViewById(getResources().getIdentifier("cb"+r,"id",getPackageName()));
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    int f;
                    String all = "";
                    for(f=1;f<4;f++){
                        CheckBox cb = findViewById(getResources().getIdentifier("cb"+f,"id",getPackageName()));
                        if (cb.isChecked()){
                            all+=cb.getText().toString() +" ";
                        }
                    }
                    tv4.setText(all);

                }
            });
        }
    }

}