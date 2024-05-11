package com.example.tcf_task_7_2;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {
    private MutableLiveData<PhoneData> listItem = new MutableLiveData<>();
    private MutableLiveData<ArrayList<PhoneData>> list = new MutableLiveData<>();

    public MutableLiveData<PhoneData> getListItem() {
        return listItem;
    }

    public MutableLiveData<ArrayList<PhoneData>> getList() {
        return list;
    }
}
