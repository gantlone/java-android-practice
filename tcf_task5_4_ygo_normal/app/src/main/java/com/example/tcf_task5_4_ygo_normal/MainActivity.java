package com.example.tcf_task5_4_ygo_normal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Activity activity = MainActivity.this;
    ArrayList<Card> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        //1
        Button bt = findViewById(R.id.bt_search);
        RecyclerView rv = findViewById(R.id.rv_card_list);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //3
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                String url = "https://db.ygoprodeck.com/api/v7/cardinfo.php?type=Normal%20Monster&num=12&offset=0";

                CardThread ct = new CardThread(activity, url);
                ct.setOnBodyUpdateListener(new CardThread.OnBodyUpdateListener() {
                    @Override
                    public void OnBodyUpd(String body) {
                        try {
                            list = WebApi.json2List(body);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        CardRecyclerViewAdapter ada = new CardRecyclerViewAdapter(
                                getApplicationContext(),
                                list
                        );
                        rv.setAdapter(ada);
                    }
                });
                ct.start();
                try {
                    // 等待CardThread完成
                    ct.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Handler handler = new Handler(Looper.getMainLooper());
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                            // 持續10秒鐘
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                        }

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "這是一個長時間顯示的Toast", Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                }).start();





            }
        });


        //4
//       rv.setAdapter(ada);

    }
}