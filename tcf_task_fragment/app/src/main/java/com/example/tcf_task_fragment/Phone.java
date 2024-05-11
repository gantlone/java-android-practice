package com.example.tcf_task_fragment;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Phone implements Serializable {
    private String name;
    private String phone;
    private int index;

    public Phone(String name, String phone, int index)  {
        this.name = name;
        this.phone = phone;
        this.index = index;
    }

    @NonNull
    @Override
    public String toString() {

        return "名稱：" +
                name + "\n" +
                "電話：" +
                phone + "\n" +
                "編號：" +
                String.valueOf(index) + "\n";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
