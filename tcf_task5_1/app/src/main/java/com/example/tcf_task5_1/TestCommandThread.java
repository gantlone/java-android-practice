package com.example.tcf_task5_1;

public class TestCommandThread extends Thread{
    private static Long counter = 0l;
    private boolean isAdd;

    public TestCommandThread(boolean isAdd) {
        this.isAdd = isAdd;
    }

    @Override
    public void run() {
        for(int i=0;i<5000;i++){
            //如不使用原子變量的話，需要調用synchronized，來達成。
            synchronized (counter) {
                if (isAdd) {
                    counter++;
                } else {
                    counter--;
                }
            }
            try{sleep(1);}catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static long getCounter(){
        return counter;
    }
}
