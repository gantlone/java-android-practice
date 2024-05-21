package com.example.tcf_task_tab_select;

import android.graphics.Bitmap;

public class Card {
    private String name;
    private Bitmap pic;

    public Card(String name, Bitmap pic) {
        this.name = name;
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getPic() {
        return pic;
    }

    public void setPic(Bitmap pic) {
        this.pic = pic;
    }
}
