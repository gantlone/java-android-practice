package com.example.tcf_task5_4_ygo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    private boolean isLandscape = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        //1
        Button bt_search = findViewById(R.id.bt_search);
        rv = findViewById(R.id.rv_card_list);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        ListView lv;
        registerForContextMenu(rv);
        //2


        //3
        String url = "https://db.ygoprodeck.com/api/v7/cardinfo.php?type=Effect%20Monster&num=12&offset=0"; //正確api
//        String url = "https://db.ygoprodeck.com/api/v7/cardinfo.php?type=Effect%20Monster&num=ewwe"; //錯誤api

        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebApiUtils.getApiDataAsync(MainActivity.this, url, new WebApiUtils.OnReadFinishedListener() {
                    @Override
                    public void OnFinished(ArrayList<Card> list) {
                        showList(list);
                    }

                    @Override
                    public void OnFail(String exception) {
                        Toast.makeText(getApplicationContext(), exception, Toast.LENGTH_SHORT).show();
                        Log.e("ygo_api_error", exception);
                    }
                });
            }
        });

        OrientationEventListener orientationEventListener = new OrientationEventListener(this) {
            @Override
            public void onOrientationChanged(int i) {
                if (i == OrientationEventListener.ORIENTATION_UNKNOWN) {
                    return;
                }

                // 根据设备方向判断是否为横屏
                if (i >= 45 && i < 135) {
                    // 横屏
                    if (!isLandscape) {
                        isLandscape = true;
                        setLandscapeLayout();
                    }
                } else if (i >= 225 && i < 315) {
                    // 反向横屏
                    if (!isLandscape) {
                        isLandscape = true;
                        setLandscapeLayout();
                    }
                } else {
                    // 竖屏
                    if (isLandscape) {
                        isLandscape = false;
                        setPortraitLayout();
                    }
                }
            }
        };
    }

    private void setLandscapeLayout() {
        // 加载横屏布局
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    private void setPortraitLayout() {
        // 加载竖屏布局
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void showList(ArrayList<Card> readList){
        CardRecyclerViewAdapter ada = new CardRecyclerViewAdapter(
                this,
                readList
        );
        ada.setOnItemClickListener(new CardRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(CardRecyclerViewAdapter.ViewHolder vh, int position, Card card) {
                Intent it = new Intent(MainActivity.this, MainActivity2.class);
                it.putExtra("card_name", card.getCard_name());
                it.putExtra("card_type", card.getCard_type());
                it.putExtra("card_image", card.getCard_image());
                startActivity(it);
            }
        });
        rv.setAdapter(ada);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opt_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.opt_all_Chk:
                break;
            case  R.id.opt_all_unChk:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.opt_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        ContextMenu.ContextMenuInfo cmi = item.getMenuInfo();
        AdapterView.AdapterContextMenuInfo acm = (AdapterView.AdapterContextMenuInfo) cmi;
        int o = acm.position;
        switch(item.getItemId()){
            case R.id.opt_all_Chk:

        }
        return super.onContextItemSelected(item);
    }
}