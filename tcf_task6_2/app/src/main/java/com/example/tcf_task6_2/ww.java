package com.example.tcf_task6_2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ww {
    private Context context;
    private String db_Name = "ww.db";
    private int version = 1;
    private www ee;
    private SQLiteDatabase db;

    public ww(Context context) {
        this.context = context;
    }

    public class www extends SQLiteOpenHelper{

        public www() {
            super(context, db_Name, null, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String aa = "";
            sqLiteDatabase.execSQL(aa);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            String aa = "";
            sqLiteDatabase.execSQL(aa);
            onCreate(sqLiteDatabase);
        }
    }

    public void open(){
        db = ee.getWritableDatabase();
    }

    public void close(){
        db.close();
    }
}
