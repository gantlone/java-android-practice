package com.example.tcf_task_list_recycler;

import android.graphics.Bitmap;

public class Card {
    private String name;
    private String atk;
    private String def;
    private Bitmap img;

    public Card(String name, String atk, String def, Bitmap img) {
        this.name = name;
        this.atk = atk;
        this.def = def;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAtk() {
        return atk;
    }

    public void setAtk(String atk) {
        this.atk = atk;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }
}
