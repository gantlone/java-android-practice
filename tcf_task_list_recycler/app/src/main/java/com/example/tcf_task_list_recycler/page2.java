package com.example.tcf_task_list_recycler;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class page2 extends AppCompatActivity {
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2);

        Intent it = getIntent();
        TextView tv;
        tv = findViewById(R.id.page_tv);
        tv.setText(it.getStringExtra("value"));
        iv = findViewById(R.id.imageView2);

        String url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRk5rmUgZ6wOzydgUZ3XGOxTbitQp9aBQq_UaWgQI7wbZ1HJHmfOTyZCJxYjdrlWVTop-Y&usqp=CAU";

        ExecutorService es = Executors.newFixedThreadPool(1);

        // 使用 FutureTask 异步加载图片
        Future<Bitmap> bitmapFuture = es.submit(new api_picture(url));
        Log.d("","123");
        try{
            Bitmap bp = bitmapFuture.get(10, TimeUnit.SECONDS);
            iv.setImageBitmap(bp);
        }catch(Exception e){}finally {
            es.shutdown();
        }

        //card_rv
        //1
        RecyclerView rv;
        rv = findViewById(R.id.rv_card);
        rv.setLayoutManager(new LinearLayoutManager(this));

        //2
        String card_url = "https://db.ygoprodeck.com/api/v7/cardinfo.php?type=Normal%20Monster&num=1000&offset=0";
        ExecutorService card_es = Executors.newFixedThreadPool(1);
        Future<String> futureBody = card_es.submit(new api_json(card_url));
        ArrayList<Card> card_list= new ArrayList<>();
        try{
            String body = futureBody.get();
            card_list = api_json.json2List(body);
        }catch(Exception e){
            e.printStackTrace();
        }
        RecyclerCardAdapter ada2 = new RecyclerCardAdapter(
                this,card_list
        );
        rv.setAdapter(ada2);


    }


}
