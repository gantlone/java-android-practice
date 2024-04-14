package com.example.tcf_task5_3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity2 extends AppCompatActivity {
    CounterThread counterThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        //1
        TextView tv = findViewById(R.id.tv_result);
        Button bt_start = findViewById(R.id.bt_start);
        Button bt_stop = findViewById(R.id.bt_stop);
        Button bt_jump = findViewById(R.id.bt_jump);

        //2
        CounterViewModel counterViewModel = new ViewModelProvider(this).get(CounterViewModel.class);
        MutableLiveData<Float> counter = counterViewModel.getCounter();
        counter.observe(this, new Observer<Float>() {
            @Override
            public void onChanged(Float aFloat) {
                String s = String.format("%.2f",aFloat);
                tv.setText(s);
            }
        });

        //3
        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String start_tag = bt_start.getText().toString();
                if(start_tag.equalsIgnoreCase("Start")){
                    counterThread = new CounterThread(MainActivity2.this);
                    bt_stop.setEnabled(true);
                    bt_start.setText("Pause");
                    counterThread.start();
                }else if(start_tag.equalsIgnoreCase("Pause")){
                    counterThread.stopCounter();
                    bt_stop.setEnabled(false);
                    bt_start.setText("Resume"); //重新開始
                }else{
                    counterThread = new CounterThread(MainActivity2.this, CounterThread.MODE_RESUME);
                    bt_stop.setEnabled(true);
                    bt_start.setText("Pause");
                    counterThread.start();
                }
            }
        });
        bt_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt_start.setText("Start");
                bt_stop.setEnabled(false);
                counterThread.stopCounter();
            }
        });

        bt_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(it);
            }
        });


    }
}
