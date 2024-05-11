package com.example.tcf_task_7_2;

import java.io.Serializable;

public class PhoneData implements Serializable {
    private String name;
    private String phone;
    private int index;

    public PhoneData(String name, String phone, int index) {
        this.name = name;
        this.phone = phone;
        this.index = index;
    }

    @Override
    public String toString() {
        return "PhoneData{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
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
