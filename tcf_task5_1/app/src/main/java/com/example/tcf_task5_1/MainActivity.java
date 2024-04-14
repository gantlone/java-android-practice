package com.example.tcf_task5_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    Button bt_start;
    Button bt_stop;
    Button bt_test;
    Button bt_test_com;
    Handler handler;
    CounterThread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        //1
        tv = findViewById(R.id.tv_result);
        bt_start = findViewById(R.id.bt_start);
        bt_stop = findViewById(R.id.bt_stop);
        bt_test = findViewById(R.id.bt_test);
        bt_test_com = findViewById(R.id.bt_test_com);

        //2
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                float f = CounterThread.getMessageData(message);
                tv.setText(String.format("%.2f",f));
                return true;
            }
        });

        //3
        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt_start.setEnabled(false);
                bt_stop.setEnabled(true);
                thread = new CounterThread(handler);
                thread.start();
            }
        });
        bt_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt_start.setEnabled(true);
                bt_stop.setEnabled(false);
                thread.stopCounter();
            }
        });
        bt_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestAtomicThread t1 = new TestAtomicThread(true);
                TestAtomicThread t2 = new TestAtomicThread(false);
                t1.start();
                t2.start();
                try {
                    t1.join(); //類似await會等待線程結束，這時會使ui進程被阻塞，導致無法使用ui，除非結束
                    t2.join();
                    Toast.makeText(MainActivity.this, "Counter= " + TestAtomicThread.getCounter(), Toast.LENGTH_SHORT).show();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        });
        bt_test_com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestCommandThread t1 = new TestCommandThread(true);
                TestCommandThread t2 = new TestCommandThread(false);
                bt_start.performClick();
                t1.start();
                t2.start();
                try {
                    t1.join(); //類似await會等待線程結束，這時會使ui進程被阻塞，導致無法使用ui，除非結束
                    t2.join();
                    bt_stop.performClick();
                    Toast.makeText(MainActivity.this, "Counter= " + TestCommandThread.getCounter(), Toast.LENGTH_SHORT).show();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

    }
}