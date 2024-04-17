package com.example.tcf_task6_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class PhoneDatabase {
    //DatabaseHelper
    private int version=1;
    private Context context;
    private static final String DB_NAME="phone3.db";
    public static final String TABLE_NAME="contact";
    public static final String KEY_NAME="name";
    public static final String KEY_PHONE="phone";
    private DatabaseHelper databaseHelper;

    private SQLiteDatabase db;

    //1
    public PhoneDatabase(Context context) {
        this.context = context;
        databaseHelper = new DatabaseHelper();
    }

    //2
    class DatabaseHelper extends SQLiteOpenHelper {


        public DatabaseHelper() {
            super(context, DB_NAME, null, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String sql = String.format("create table %s (" + "_id INTEGER PRIMARY KEY AUTOINCREMENT," + " %s text, %s text)",
                    TABLE_NAME, KEY_NAME, KEY_PHONE);
            Log.d("create sql",sql);
            sqLiteDatabase.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            String sql = String.format("drop table if exists %s", TABLE_NAME);
            Log.d("drop sql",sql);
            sqLiteDatabase.execSQL(sql);
            onCreate(sqLiteDatabase);
        }

    }

    //3
    public void open(){
        if(db==null || !db.isOpen()){
            db=databaseHelper.getWritableDatabase();
        }
    }

    public void close(){
        if(db!=null && db.isOpen()){
            db.close();
        }
    }

    public void drop(){
        String sql = String.format("drop table if exists %s", TABLE_NAME);
        db.execSQL(sql);
        databaseHelper.onCreate(db);
    }

    public Cursor queryAll(){
        String sql = String.format("select * from %s", TABLE_NAME);
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public long insertData(String name, String phone){
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, name);
        cv.put(KEY_PHONE, phone);
        return db.insert(TABLE_NAME, null, cv);
    }

}
