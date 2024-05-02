package com.example.tcf_task_list_recycler;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

public class student {
    private int student_number;
    private String student_name;
    private Drawable student_pic_num;

    public student(int student_number, String student_name, Drawable student_pic_num) {
        this.student_number = student_number;
        this.student_name = student_name;
        this.student_pic_num = student_pic_num;
    }

    public int getStudent_number() {
        return student_number;
    }

    public void setStudent_number(int student_number) {
        this.student_number = student_number;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public Drawable getStudent_pic_num() {
        return student_pic_num;
    }

    public void setStudent_pic_num(Drawable student_pic_num) {
        this.student_pic_num = student_pic_num;
    }
}
