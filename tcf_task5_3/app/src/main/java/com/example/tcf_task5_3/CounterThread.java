package com.example.tcf_task5_3;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import java.util.concurrent.atomic.AtomicBoolean;

public class CounterThread extends Thread{
    private AtomicBoolean isRunning;
    private ViewModelStoreOwner owner;
    private MutableLiveData<Float> counter;
    private int mode;
    public static final int MODE_RESTART = 0;
    public static final int MODE_RESUME = 1;

    public CounterThread(ViewModelStoreOwner owner, int mode) {
        this.owner = owner;
        this.mode = mode;
    }

    public CounterThread(ViewModelStoreOwner owner) {
        this.owner = owner;
        mode = MODE_RESTART;
    }

    @Override
    public void run() {
        isRunning = new AtomicBoolean(true);
        CounterViewModel counterViewModel = new ViewModelProvider(owner).get(CounterViewModel.class);
        counter = counterViewModel.getCounter();
        if(mode==MODE_RESUME){
            counter.postValue(0.0f);
        }
        while (isRunning.get()){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter.postValue(counter.getValue()+0.01f);
        }
    }

    public void stopCounter(){
        isRunning.set(false);
    }
}
