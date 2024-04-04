package com.example.tcf_task2_5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements CompoundButton.OnCheckedChangeListener {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);
        tv = findViewById(R.id.textView2);
        setListener();
    }
    private void setListener() {
        for (int i = 1; i < 4; i++) {
            CheckBox cb = findViewById(getResources().getIdentifier("checkBox" + i, "id", getPackageName()));
            if (cb != null) {
                cb.setOnCheckedChangeListener(this); // 设置监听器
            }
        }
    }

    private void updChange() {
        String s = "";
        for (int i = 1; i < 4; i++) {
            CheckBox cb = findViewById(getResources().getIdentifier("checkBox" + i, "id", getPackageName()));
            if (cb != null && cb.isChecked()) {
                s += cb.getText() + " ";
            }
        }
        tv.setText(s);
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        updChange();
    }
}