package com.example.tcf_task5_2;

import android.app.Activity;

import java.util.concurrent.atomic.AtomicBoolean;

public class CounterThread extends Thread{
    private AtomicBoolean isRunning;
    private Activity activity;
    private float counter;
    private OnUpdateListener l;

    public CounterThread(Activity activity) {
        this.activity = activity;
    }

    public interface OnUpdateListener{
        void onUpdate(float counter);
    }

    public void setOnUpdateListener(OnUpdateListener l){
        this.l=l;
    }

    @Override
    public void run() {
        isRunning = new AtomicBoolean(true);
        counter = 0.0f;
        while(isRunning.get()){
            try{
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter += 0.01f;

            //利用Activity對象，切換到主ui
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(l!=null){
                        l.onUpdate(counter);
                    }
                }
            });
        }
    }

    public void stopCounter(){
        isRunning.set(false);
    }
}
