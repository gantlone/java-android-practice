package com.example.tcf_task5_4_ygo_normal;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class WebApi {

    public static String fetchData(String strUrl) throws IOException{
        StringBuilder result = new StringBuilder();

        try{
            URL url= new URL(strUrl);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            conn.connect(); //重要

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                String line;
                while ((line=reader.readLine())!=null){
                    result.append(line);
                }
                reader.close();
            }else{
                Log.e("api串接錯誤", "連接失敗，錯誤碼：" + conn.getResponseCode());
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            // URL格式錯誤
            Log.e("api串接錯誤", "URL格式錯誤: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            // IO錯誤，比如連接超時等
            Log.e("api串接錯誤", "IO錯誤: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            // 其他異常
            Log.e("api串接錯誤", "發生異常: " + e.getMessage());
        }
        return result.toString();
    }

    public static ArrayList<Card> json2List(String body) throws JSONException  {
        JSONObject cardBody = new JSONObject(body);
        JSONArray cardData = cardBody.getJSONArray("data");
        ArrayList<Card> list = new ArrayList<>();
        for(int i=0;i<cardData.length();i++){
            JSONObject cardDetail = cardData.getJSONObject(i);
            String name = stringSafety(cardDetail,"name");
            String type = stringSafety(cardDetail,"type");
            list.add(new Card(name, type));
        }

        return list;
    }

    public static String stringSafety(JSONObject jsonObject, String key){
        String result = "";
        try{
            result = jsonObject.getString(key);
        }catch(Exception e){

        }

        return result;
    }
}
