package com.example.tcf_task_api;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class api {

    //content
    public static String content(String http){
        StringBuilder sb = new StringBuilder();
        try{
            URL url = new URL(http);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStream inputStream = conn.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while((line=bufferedReader.readLine())!=null){
                    sb.append(line);
                }
                inputStream.close();
                conn.disconnect();
            }

        }catch (Exception e){
            Log.e("api_content：", e.getMessage());
        }
        return  sb.toString();
    }

    public static Bitmap picture(String http){
        Bitmap bp=null;

        try{
            URL url = new URL(http);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStream inputStream = conn.getInputStream();

                bp = BitmapFactory.decodeStream(inputStream);

                conn.disconnect();
                inputStream.close();
            }


        }catch(Exception e){
            Log.e("api_picture：", e.getMessage());
        }

        return bp;
    }

    public static ArrayList<Card> listContent(String body) throws JSONException, InterruptedException {
        ArrayList<Card> list = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(body);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        for(int i=0;i<jsonArray.length();i++){
            JSONObject jsonItemObject = jsonArray.getJSONObject(i);
            String name = jsonItemObject.getString("name");
            String atk = jsonItemObject.getString("atk");
            String def = jsonItemObject.getString("def");


            JSONArray arr_img = jsonItemObject.getJSONArray("card_images");
            JSONObject obj_img = arr_img.getJSONObject(0);
            String img = obj_img.getString("image_url");
            final Bitmap[] bp = new Bitmap[1];
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    bp[0] = picture(img);
                }
            });
            thread.start();
            thread.join();
            list.add(new Card(name,atk,def, bp[0]));
        }

        return list;
    }
}
