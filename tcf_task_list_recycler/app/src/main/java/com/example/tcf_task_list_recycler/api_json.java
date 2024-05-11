package com.example.tcf_task_list_recycler;

import android.graphics.Bitmap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class api_json implements Callable<String> {
    private String bodyUrl;


    public api_json(String bodyUrl) {
        this.bodyUrl = bodyUrl;
    }

    @Override
    public String call() throws Exception {
        try{
            URL url = new URL(bodyUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder body = new StringBuilder();
                String line;
                while ((line=bufferedReader.readLine())!=null){
                    body.append(line);
                }
                inputStream.close();
                connection.disconnect();
                return body.toString();
            }
        }catch (Exception e){e.printStackTrace();}
        return "";
    }

    public static ArrayList<Card> json2List(String body) throws JSONException {
        JSONObject all = new JSONObject(body); //{
        JSONArray data = all.getJSONArray("data"); //[
        ArrayList<Card> list = new ArrayList<>();
//        ExecutorService es = Executors.newFixedThreadPool(1);
        for(int i=0;i<data.length();i++){
            JSONObject obj = data.getJSONObject(i);
            String atk = safetyString(obj,"atk");
            String def = safetyString(obj,"def");
            String name = safetyString(obj,"name");
            Bitmap bp = null;
//            JSONArray imgs = obj.getJSONArray("card_images");
//            JSONObject img_obj = imgs.getJSONObject(0);
//
//            Future<Bitmap> futureImg = es.submit(new api_picture(safetyString(img_obj,"image_url")));
//            Bitmap bp=null;
//            try{
//                bp = futureImg.get();
//            }catch(Exception e){
//                e.printStackTrace();
//            }
            list.add(new Card(name,atk,def,bp));
        }
//        es.shutdown();
        return list;
    }

    private static String safetyString(JSONObject data, String key){
        try{
            String s = data.getString(key);
            return s;
        }catch(Exception e){
            e.printStackTrace();
            return "";
        }
    }
}
