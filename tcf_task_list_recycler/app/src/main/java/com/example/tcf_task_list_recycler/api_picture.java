package com.example.tcf_task_list_recycler;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.UrlQuerySanitizer;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

public class api_picture implements Callable<Bitmap> {
    private String imgUrl;

    public api_picture(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public Bitmap call() throws Exception {
        Bitmap bp = null;

        try{
            URL url = new URL(imgUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStream inputStream = connection.getInputStream();
                bp = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
                connection.disconnect();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return bp;
    }
}

