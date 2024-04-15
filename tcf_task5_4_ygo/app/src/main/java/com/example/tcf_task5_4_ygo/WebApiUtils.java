package com.example.tcf_task5_4_ygo;

import android.app.Activity;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WebApiUtils {
    private static OkHttpClient client;

    //介接客戶端api連線
    private static OkHttpClient getClient(){
        synchronized (Activity.class){
            if(client==null){
                client = new OkHttpClient();
            }
        }
        return client;
    }

    public interface OnReadFinishedListener{
        void OnFinished(ArrayList<Card> list);

        void OnFail(String exception);
    }

    public static void getApiDataAsync(Activity activity, String url, OnReadFinishedListener l){
        //1 介接OkHttp 客戶端
        OkHttpClient c = getClient();

        //2.創建需求，對應的網址
        Request request = new Request.Builder().url(url).get().build();

        //3.創建call，並使用enqueue來做async動作
        //使用activity.runOnUiThread，來更新ui上面內容，並針對請求成功(onResponse)、失敗(onFailure)做相應回覆。
        Call call = c.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        l.OnFail(e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try{
                    if(response.isSuccessful()){
                        String body = response.body().string();
                        ArrayList<Card> list =CardJsonReverse.json2CardList(body);
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                l.OnFinished(list);
                            }
                        });
                    }else{
                        switch (response.code()){
                            case 400:
                                String body = response.body().string();
                                String err =CardJsonReverse.json2Err(body);
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        l.OnFail(err);
                                    }
                                });
                                break;
                        }

                    }

                }catch(Exception e){
                    e.printStackTrace();
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            l.OnFail(e.toString());
                        }
                    });
                }

            }
        });
    }
}
