package com.example.tcf_task5_4;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CardParsingUtils {
    public static List<CardSet> json2ListByJsonObj(String s) throws JSONException {
        //1
        List<CardSet> list = new ArrayList<>();
        if(!TextUtils.isEmpty(s)){
            JSONArray jsonArray = new JSONArray(s);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String set_name = jsonObject.getString("set_name");
                String set_code = jsonObject.getString("set_code");
                int num_of_cards = jsonObject.getInt("num_of_cards");
                String tcg_date = jsonObject.getString("tcg_date");
                String set_image;

                try{
                    set_image = jsonObject.getString("set_image");
                }catch(Exception e){
                    set_image = "沒有圖片";
                }

                CardSet cardSet = new CardSet(set_name,set_code,num_of_cards,tcg_date,set_image);
                list.add(cardSet);
            }
        }

        return list;
    }
}
