package com.example.tcf_task5_1;

import java.util.concurrent.atomic.AtomicLong;

public class TestAtomicThread extends Thread{
    private static AtomicLong counter = new AtomicLong(0);
    private boolean isAdd;

    public TestAtomicThread(boolean isAdd) {
        this.isAdd = isAdd;
    }

    @Override
    public void run() {
        for(int i=0;i<5000;i++){
            if(isAdd){
                counter.getAndAdd(1);
            }else{
                counter.getAndAdd(-1);
            }
            try{
                sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static AtomicLong getCounter(){
        return counter;
    }

    public static void resetCounter(){
        counter.set(01);
    }
}
