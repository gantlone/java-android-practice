package com.example.tcf_task5_1;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;

public class CounterThread extends Thread{
    private static final String KEY_COUNTER = "key_counter";
    private AtomicBoolean isRunning; //使用原子變量，使多線程安全
    private Handler handler; //handler，透過主線程傳遞，給後台使用

    public CounterThread(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        isRunning = new AtomicBoolean(true);
        float counter = 0.0f;
        while (isRunning.get()){
            try{
                Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
                Log.e("非執行續錯誤", e.getMessage());
            }
            counter+=0.01f;
            Bundle bd = new Bundle();
            bd.putFloat(KEY_COUNTER, counter);
            Message msg = handler.obtainMessage();
            msg.setData(bd);
            handler.sendMessage(msg);
        }
    }

    public void stopCounter(){
        isRunning.set(false);
    }

    public static float getMessageData(Message msg){
        Bundle bd = msg.getData();
        float v = bd.getFloat(KEY_COUNTER);
        return v;

    }
}
