package com.example.tcf_task5_4_ygo_normal;

import android.app.Activity;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class CardThread extends Thread{
    private Activity activity;
    private String url;
    private String body;
    OnBodyUpdateListener l;

    public CardThread(Activity activity, String url) {
        this.activity = activity;
        this.url = url;
    }

    interface OnBodyUpdateListener{
        void OnBodyUpd(String body);
    }

    public void setOnBodyUpdateListener(OnBodyUpdateListener l){
        this.l = l;
    }

    @Override
    public void run() {
        try {
            body = WebApi.fetchData(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                l.OnBodyUpd(body);
            }
        });
    }
}
