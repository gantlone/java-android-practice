package com.example.tcf_task3_6;

public class City {
    private String name;
    private String phone;
    private int picId;

    public City(String name, String phone, int picId) {
        this.name = name;
        this.phone = phone;
        this.picId = picId;
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

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }
}