package com.example.tcf_task_fragment.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.tcf_task_fragment.Card;

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
    public static String api_json(String http){
        StringBuilder sb =null;
        try{
            URL url = new URL(http);
           HttpURLConnection conn= (HttpURLConnection) url.openConnection();
           conn.connect();
           if(conn.getResponseCode()==HttpURLConnection.HTTP_OK){
               InputStream is = conn.getInputStream();

               BufferedReader br = new BufferedReader(new InputStreamReader(is));

               String line;
               while((line=br.readLine())!=null){
                   sb.append(line);
               }

               is.close();
           }

           conn.disconnect();

        }catch(Exception E){

        }

        return sb.toString();
    }

    public static Bitmap api_pic(String http){
        Bitmap bp =null;
        try{
            URL url = new URL(http);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.connect();
            if(conn.getResponseCode()==HttpURLConnection.HTTP_OK){
                InputStream is = conn.getInputStream();

                bp = BitmapFactory.decodeStream(is);

                is.close();
            }

            conn.disconnect();

        }catch(Exception E){

        }

        return bp;
    }

    public ArrayList<Card> convert(String body) throws JSONException {
        ArrayList<Card> list = new ArrayList<>();
        JSONObject jb = new JSONObject(body);
        JSONArray ja = jb.getJSONArray("data");
        for(int i=0;i<ja.length();i++){
            JSONObject jbb = ja.getJSONObject(i);
            String name = jbb.getString("name");
            JSONArray pic = jbb.getJSONArray("card_images");
            JSONObject pico = pic.getJSONObject(0);
            String aa = pico.getString("image_url");
            final Bitmap[] bp = {null};
            Thread t= new Thread(new Runnable() {
                @Override
                public void run() {
                    bp[0] = api_pic(aa);
                }
            });

            t.start();
            try{
                t.join();
            }catch(Exception e){

            }

            list.add(new Card(name, bp[0]));
        }

        return list;
    }
}
