//package com.example.myapplication;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.view.ContextMenu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//import java.util.Random;
//
//public class MainActivity extends AppCompatActivity {
//    TextView tv;
//    ListView lv;
//    ArrayList<String> list;
//    ArrayAdapter<String> ada;
//
//    int position;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.my_main);
//
//        //1
//        tv= findViewById(R.id.tv_result);
//        lv = findViewById(R.id.listView);
//
//        //2
//        list = new ArrayList<>();
//        for(int i=0;i<30;i++){
//            list.add(String.format("item %02d",i));
//        }
//        ada = new ArrayAdapter<>(
//              this,
//              android.R.layout.simple_list_item_1,
//              list
//        );
//
//        //3
//        registerForContextMenu(lv);
//        registerForContextMenu(tv);
//
//        //4
//        lv.setAdapter(ada);
//
//
//    }
//
//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        if(v == tv){
//            getMenuInflater().inflate(R.menu.ctx_tv_menu, menu);
//        }else{
//            getMenuInflater().inflate(R.menu.ctx_menu, menu);
//            AdapterView.AdapterContextMenuInfo acm = (AdapterView.AdapterContextMenuInfo) menuInfo;
//            position = acm.position;
//        }
//
//    }
//
//    @Override
//    public boolean onContextItemSelected(@NonNull MenuItem item) {
//        Log.d("item id", String.valueOf(item.getItemId()));
//        switch (item.getItemId()){
//            case R.id.ctx_insert:
//                String rd = String.valueOf(new Random().nextInt(1000));
//                list.add(position, rd);
//                ada.notifyDataSetChanged();
//                break;
//            case R.id.ctx_delete:
//                list.remove(position);
//                ada.notifyDataSetChanged();
//                break;
//            case R.id.ctx_tv_12:
//                Log.d("12sp","12");
//                tv.setText("1");
//                break;
//            case R.id.ctx_tv_16:
//                Log.d("16sp","16");
//                tv.setText("2");
//                break;
//        }
//
//        return super.onContextItemSelected(item);
//
//
//    }
//}