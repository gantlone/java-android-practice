package com.example.tcf_task6_2;

public class Phone {
    private int phone_id;
    private String phone_name;
    private String phone_number;

    public Phone(int phone_id, String phone_name, String phone_number) {
        this.phone_id = phone_id;
        this.phone_name = phone_name;
        this.phone_number = phone_number;
    }

    public int getPhone_id() {
        return phone_id;
    }

    public void setPhone_id(int phone_id) {
        this.phone_id = phone_id;
    }

    public String getPhone_name() {
        return phone_name;
    }

    public void setPhone_name(String phone_name) {
        this.phone_name = phone_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
