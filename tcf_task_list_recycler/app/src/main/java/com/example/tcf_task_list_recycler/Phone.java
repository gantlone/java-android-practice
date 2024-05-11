package com.example.tcf_task_list_recycler;

public class Phone {

    private int _id;
    private String name;
    private String phone;

    public Phone(int _id, String name, String phone) {
        this._id = _id;
        this.name = name;
        this.phone = phone;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
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

    public void setYearOld(String phone) {
        this.phone = phone;
    }
}
