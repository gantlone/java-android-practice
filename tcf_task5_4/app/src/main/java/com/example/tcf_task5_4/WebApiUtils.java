package com.example.tcf_task5_4;

import android.app.Activity;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.nio.channels.AcceptPendingException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WebApiUtils {
    private static OkHttpClient client;

    //取得客戶端，並使用synchronized加鎖，避免多方呼叫導致重複生成，WebApiUtils是singleton
    private static OkHttpClient getClient(){
        synchronized (WebApiUtils.class){
            if(client==null){
                client = new OkHttpClient();
            }
        }
        return client;
    }

    public interface OnReadFinishedListener{
        public void onFinished(List<CardSet> list);

        public void onFail(String e);
    }

    //取得資料環節，並針對呼叫成功與失敗，對應listener
    public static void getApiDataAsync(Activity activity, String url, OnReadFinishedListener l){
        OkHttpClient connect = getClient();
        Request request = new Request.Builder().url(url).get().build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        l.onFail(e.toString());
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try{
                    String s = response.body().string();
                    List<CardSet> list = CardParsingUtils.json2ListByJsonObj(s);
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            l.onFinished(list);
                        }
                    });

                }catch (Exception e){
                    e.printStackTrace();
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            l.onFail(e.toString());
                        }
                    });
                }
            }
        });
    }



}
