package com.example.tcf_task5_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        //1
        lv = findViewById(R.id.lv_card_data);
        EditText et = findViewById(R.id.et_input);
        findViewById(R.id.bt_async).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = et.getText().toString().trim();
                WebApiUtils.getApiDataAsync(MainActivity.this, url, new WebApiUtils.OnReadFinishedListener() {
                    @Override
                    public void onFinished(List<CardSet> list) {
                        showList(list);
                    }

                    @Override
                    public void onFail(String e) {
                        Toast.makeText(getApplicationContext(), e, Toast.LENGTH_SHORT).show();
                        Log.e("ygo_api_error", e);
                    }
                });
            }
        });

    }

    public void showList(List<CardSet> readList){
        ArrayAdapter<CardSet> ada = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                readList
        );
        lv.setAdapter(ada);
    }

}