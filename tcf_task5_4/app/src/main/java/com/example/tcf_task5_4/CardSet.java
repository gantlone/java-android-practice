package com.example.tcf_task5_4;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class CardSet {
    public String set_name;

    @SerializedName("set_code")
    public String set_code;
    public int num_of_cards;
    public String tcg_date;
    public String set_image;

    public CardSet(String set_name, String set_code, int num_of_cards, String tcg_date, String set_image) {
        this.set_name = set_name;
        this.set_code = set_code;
        this.num_of_cards = num_of_cards;
        this.tcg_date = tcg_date;
        this.set_image = set_image;
    }

    public CardSet(String set_name, String set_code, int num_of_cards, String tcg_date) {
        this.set_name = set_name;
        this.set_code = set_code;
        this.num_of_cards = num_of_cards;
        this.tcg_date = tcg_date;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("卡包名稱:%s\n卡包序號:%s\n收入數量:%2d\n開賣日期:%s\n圖片網址:%s\n",set_name,set_code,num_of_cards,tcg_date,set_image);
    }
}
