package com.example.tcf_task5_4_ygo_normal;

public class Card {
    private String card_name;
    private String card_type;

    public Card(String card_name, String card_type) {
        this.card_name = card_name;
        this.card_type = card_type;
    }

    public String getCard_name() {
        return card_name;
    }

    public void setCard_name(String card_name) {
        this.card_name = card_name;
    }

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

}
