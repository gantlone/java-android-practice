package com.example.tcf_task_list_recycler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class PhoneSQL{
    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    private int version = 1;
    private static final String db_name = "phone.db";
    private static final String sql_phone = "phone";
    private static final String sql_name = "name";
    private static final String table_name = "contact";

    public PhoneSQL(Context context) {
        this.context = context;
        databaseHelper = new DatabaseHelper();
    }

    class DatabaseHelper extends SQLiteOpenHelper{

        public DatabaseHelper() {
            super(context, db_name, null, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String sql = String.format("create table %s ( _id integer primary key autoincrement,  %s varchar(10), %s char(10))", table_name,sql_name,sql_phone);
            Log.d("create", sql);
            sqLiteDatabase.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            String sql = String.format("drop table if exist %s", table_name);
            sqLiteDatabase.execSQL(sql);
            onCreate(sqLiteDatabase);
        }


    }

    public void open(){
        if(db==null || !db.isOpen()){
            db = databaseHelper.getWritableDatabase();
        }
    }

    public void close(){
        if(db!=null && db.isOpen()){
            db.close();
        }
    }

    public void drop(){
        String sql = String.format("drop table if exist %s", table_name);
        db.execSQL(sql);
        databaseHelper.onCreate(db);
    }

    public long insert(String name, String phone){
        ContentValues cv = new ContentValues();
        cv.put(sql_name, name);
        cv.put(sql_phone, phone);
        return  db.insert(table_name, null, cv);

    }

    public Cursor selectAll(){
        String sql = String.format("select _id, name, phone from %s", table_name);
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public void del(Phone pn){
        String[] where = new String[]{String.valueOf(pn.get_id()),pn.getName(), pn.getPhone()};
        db.delete(table_name, "_id = ?, name = ?, phone = ? ", where);
    }




}
