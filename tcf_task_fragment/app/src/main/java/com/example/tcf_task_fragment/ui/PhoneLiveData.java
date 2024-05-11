package com.example.tcf_task_fragment.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tcf_task_fragment.Phone;

import java.util.ArrayList;

public class PhoneLiveData extends ViewModel {
    private MutableLiveData<ArrayList<Phone>> listLiveData = new MutableLiveData<>();
    private MutableLiveData<Phone> itemLiveData = new MutableLiveData<>();

    public MutableLiveData<ArrayList<Phone>> getListLiveData() {
        return listLiveData;
    }

    public MutableLiveData<Phone> getItemLiveData() {
        return itemLiveData;
    }
}
