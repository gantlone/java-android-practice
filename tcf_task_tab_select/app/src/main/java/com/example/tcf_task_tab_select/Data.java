package com.example.tcf_task_tab_select;

import java.io.Serializable;

public class Data implements Serializable {
    private String name;
    private int index;
    private String sex;

    public Data(String name, int index, String sex) {
        this.name = name;
        this.index = index;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return name + '\n' +
               index + '\n' +
                sex + '\n';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
