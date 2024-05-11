package com.example.tcf_task_7_1;

import android.os.Bundle;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class PhoneData implements Serializable {
    private String name;
    private String phone;
    private int position = 0;

    public PhoneData(String name, String phone, int position) {
        this.name = name;
        this.phone = phone;
        this.position = position;
    }

    public PhoneData(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public static void putPhoneData(Bundle bundle, PhoneData phoneData){
        bundle.putSerializable("phone_data", phoneData);
    }

    public static PhoneData getPhoneData(Bundle bundle){
        return (PhoneData) bundle.getSerializable("phone_data");
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("%s:%s", name, phone);
    }
}
