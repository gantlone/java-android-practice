package com.example.tcf_task4_5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<PhoneData> list;
    ArrayAdapter<PhoneData> ada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        //1
        lv = findViewById(R.id.listView);

        //2
        list = new ArrayList<>();;
        for (int i=0; i<30; i++){
            list.add(new PhoneData("羅成員", "0987878787"));
            list.add(new PhoneData("廖昱婷", "0912121212"));
        }
        ada = new ArrayAdapter<>(
          this,
          android.R.layout.simple_list_item_1,
          list
        );

        //3
        registerForContextMenu(lv);

        //4
        lv.setAdapter(ada);

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
        int position = ami.position;

        switch (item.getItemId()){
            case R.id.ctx_add:
                addData(position);
                break;
            case R.id.ctx_edit:
                updData(position);
                break;
        }

        return super.onContextItemSelected(item);
    }

    private void addData(int position){
        PhoneDataDialog pdd = new PhoneDataDialog(this, "新增");
        pdd.showDialog(null, new PhoneDataDialog.OnSubmitListener() {
            @Override
            public void onSubmit(PhoneData updateData) {
                list.add(position,updateData);
                ada.notifyDataSetChanged();
            }
        });
    }

    private void updData(int position){
        PhoneData pd = list.get(position);
        PhoneDataDialog pdd = new PhoneDataDialog(this, "修改");
        pdd.showDialog(pd, new PhoneDataDialog.OnSubmitListener() {
            @Override
            public void onSubmit(PhoneData updateData) {
                pd.setName(updateData.getName());
                pd.setPhone(updateData.getPhone());
                ada.notifyDataSetChanged();
            }
        });
    }


}