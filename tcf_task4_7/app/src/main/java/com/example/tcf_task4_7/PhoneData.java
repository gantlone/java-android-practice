package com.example.tcf_task4_7;

public class PhoneData {
    private String name;
    private String phone;
    private boolean isChecked = false;

    public PhoneData(String name, String phone, boolean isChecked) {
        this.name = name;
        this.phone = phone;
        this.isChecked = isChecked;
    }

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

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
