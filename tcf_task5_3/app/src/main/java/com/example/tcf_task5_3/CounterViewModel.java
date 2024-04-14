package com.example.tcf_task5_3;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CounterViewModel extends ViewModel {
    private MutableLiveData<Float> counter;

    public CounterViewModel() {
        counter = new MutableLiveData<>();
        counter.setValue(0.0f);
    }

    public MutableLiveData<Float> getCounter(){
        return counter;
    }
}
