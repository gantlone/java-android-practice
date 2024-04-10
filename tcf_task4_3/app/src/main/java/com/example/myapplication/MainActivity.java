package com.example.myapplication;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import kotlin.random.Random;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    ListView lv;
    ArrayList<String> list = new ArrayList<>();
    ArrayAdapter<String> ada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        //1
        tv = findViewById(R.id.tv_result);
        lv = findViewById(R.id.listView);

        //2
        for(int i=0; i<100; i++){
            list.add(String.format("羅成員%02d",i));
        }
        ada = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                list
        );

        //3
        registerForContextMenu(lv);
        tv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showTextViewPopUpMenu(view);
                return true;
            }
        });

        //4
        lv.setAdapter(ada);
    }

    private void showTextViewPopUpMenu(View v){
        //對該view產生popupmenu實例
        PopupMenu pm = new PopupMenu(getApplicationContext(),v);

        //取得該view
        pm.getMenuInflater().inflate(R.menu.ctx_tv_menu, pm.getMenu());

        pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.ctx_tv_12:
                        tv.setText("羅成員");
                        tv.setTextSize(12.0f);
                        break;
                    case R.id.ctx_tv_16:
                        tv.setText("羅成員愛小時投");
                        tv.setTextSize(16.0f);
                        break;
                }
                return true;
            }
        });

        //跟Toast一樣要加show()
        pm.show();
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.ctx_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        ContextMenu.ContextMenuInfo cmi = item.getMenuInfo();
        AdapterView.AdapterContextMenuInfo ami = (AdapterView.AdapterContextMenuInfo) cmi;
        int i = ami.position;
        switch (item.getItemId()){
            case R.id.ctx_insert:
                int r = new java.util.Random().nextInt(100);
                String rr = String.valueOf(r);
                list.add(i, rr);
                break;
            case R.id.ctx_delete:
                list.remove(i);
                Toast.makeText(this,"已刪除",Toast.LENGTH_SHORT).show();
                break;
        }
        ada.notifyDataSetChanged();
        return super.onContextItemSelected(item);

    }
}
