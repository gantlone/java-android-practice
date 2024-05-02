package com.example.tcf_task_list_recycler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        //1
        ListView lv = findViewById(R.id.lv_view);
        RecyclerView rv = findViewById(R.id.rv_view);
        Switch sw = findViewById(R.id.switch1);
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        registerForContextMenu(lv);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                }else {
                    rv.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
                }
            }
        });

        //2
        ArrayList<student> list = new ArrayList<>();
        String first_name[] = {"王","陳","林","柔","雄","酥"};
        String sec_name[] = {"成員","文案","冠憲","八但","稚齡"};
        Random rm = new Random();
        TypedArray ta = getResources().obtainTypedArray(R.array.img_view);
        int first_num, sec_num;
        for(int i=0;i<30;i++){
            first_num = rm.nextInt(6);
            sec_num = rm.nextInt(5);
            int draw_num = rm.nextInt(3);
            student people = new student(i+1,first_name[first_num]+sec_name[sec_num], ta.getDrawable(draw_num));
            list.add(people);
        }

        //3
        ListViewAdapter ada = new ListViewAdapter(
            this,
            android.R.layout.simple_list_item_1,
            list
        );
        RecyclerViewAdapter ada2 = new RecyclerViewAdapter(this,list);

        //3.5
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "listview_" + i, Toast.LENGTH_SHORT).show();
            }
        });
        ada2.setOnItemClickListener2(new RecyclerViewAdapter.OnItemClick() {
             @Override
             public void itemClick(student s, int position, View v) {
                 PopupMenu pm = new PopupMenu(MainActivity.this, v);
                 pm.getMenuInflater().inflate(R.menu.nl_roger_menu, pm.getMenu());
                 pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                     @Override
                     public boolean onMenuItemClick(MenuItem item) {
                         switch (item.getItemId()){
                             case R.id.roger:
                                 Intent it = new Intent(MainActivity.this, page2.class);
                                 it.putExtra("value", "123");
                                 startActivity(it);
                                 break;
                             case R.id.neverlose:
                                 AlertDialog.Builder bi = new AlertDialog.Builder(MainActivity.this);
                                 bi.setNegativeButton("取消",null);
                                 bi.setTitle("雄寶社團");
                                 String[] s = {"羅成員","雄文案"};
                                 boolean[] b = {false,false};
                                 bi.setMultiChoiceItems(s, b, new DialogInterface.OnMultiChoiceClickListener() {
                                     @Override
                                     public void onClick(DialogInterface dialogInterface, int i, boolean c) {
                                         if(c){
                                             b[i]=true;
                                         }else{
                                             b[i]=false;
                                         }
                                     }
                                 });
                                 bi.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                     @Override
                                     public void onClick(DialogInterface dialogInterface, int i) {
                                         String all="";
                                        for(int ii=0;ii<b.length;ii++){
                                            all+=s[ii];

                                        }
                                         Toast.makeText(MainActivity.this, all, Toast.LENGTH_SHORT).show();
                                     }
                                 });
                                 bi.show();
                                 break;
                         }
                         return true;
                     }
                 });
                 pm.show();
             }
      });


        //4
        lv.setAdapter(ada);
       rv.setAdapter(ada2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nl_roger_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.roger:
                Toast.makeText(this, "柔成員", Toast.LENGTH_SHORT).show();
                break;
            case R.id.neverlose:
                Toast.makeText(this, "雄文案", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.nl_roger_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        ContextMenu.ContextMenuInfo cmi = item.getMenuInfo();
        AdapterView.AdapterContextMenuInfo acm = (AdapterView.AdapterContextMenuInfo) cmi;
        int position = acm.position;
        switch (item.getItemId()){
            case R.id.roger:
                Toast.makeText(getApplicationContext(), "柔成員"+position, Toast.LENGTH_SHORT).show();
                break;
            case R.id.neverlose:
                Toast.makeText(MainActivity.this, "雄文案"+position, Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }
}