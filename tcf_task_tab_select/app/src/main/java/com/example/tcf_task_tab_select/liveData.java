package com.example.tcf_task_tab_select;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class liveData extends ViewModel {
    private MutableLiveData<ArrayList<Data>> listLiveData = new MutableLiveData<>();
    private MutableLiveData<Data> itemLiveData = new MutableLiveData<>();

    public MutableLiveData<ArrayList<Data>> getListLiveData() {
        return listLiveData;
    }

    public MutableLiveData<Data> getItemLiveData() {
        return itemLiveData;
    }
}
