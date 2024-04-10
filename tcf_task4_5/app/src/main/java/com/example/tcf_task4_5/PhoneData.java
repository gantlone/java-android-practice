package com.example.tcf_task4_5;

import androidx.annotation.NonNull;

public class PhoneData {
    private String name;
    private String phone;

    public PhoneData(String name, String phone) {
        this.name = name;
        this.phone = phone;
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

    @NonNull
    @Override
    public String toString() {
        return String.format("%s %s" ,name ,phone);
    }
}
