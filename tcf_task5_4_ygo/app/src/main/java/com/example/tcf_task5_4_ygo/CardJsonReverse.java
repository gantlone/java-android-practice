package com.example.tcf_task5_4_ygo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CardJsonReverse {

    public static ArrayList<Card> json2CardList(String body) throws JSONException {
        ArrayList<Card> list = new ArrayList<>();
        if(!body.isEmpty()) {
            JSONObject jsonObject_body = new JSONObject(body);
            JSONArray jsonArray_data = jsonObject_body.getJSONArray("data");
            for(int i=0;i<jsonArray_data.length();i++){
                JSONObject jsonObject_card = jsonArray_data.getJSONObject(i);
                String card_name = getStringSafely(jsonObject_card, "name");
                String card_type = getStringSafely(jsonObject_card, "type");

                JSONArray jsonObject_card_images = jsonObject_card.getJSONArray("card_images");
                String card_image = getStringSafely(jsonObject_card_images.getJSONObject(0), "image_url");

                list.add(new Card(card_name,card_type,card_image));
            }
        }

        return list;
    }

    public static String json2Err(String body) throws JSONException{
        if(!body.isEmpty()) {
            JSONObject jsonObject_body = new JSONObject(body);
            String err = "status code 400: " + jsonObject_body.getString("error");
            return err;
        }
        return "400錯誤";
    }

    public static String getStringSafely(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getString(key);
        } catch (JSONException e) {
            return ""; // 如果出现异常，则返回空字符串
        }
    }

}
