package com.example.tcf_task_livedata;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class viewModel extends ViewModel {
    private MutableLiveData<Boolean> liveData = new MutableLiveData<>();

    public LiveData<Boolean> getLiveData() {
        liveData.setValue(true);
        return liveData;
    }

    public void setLiveDataValue(boolean value) {
        liveData.setValue(value);
    }
}
