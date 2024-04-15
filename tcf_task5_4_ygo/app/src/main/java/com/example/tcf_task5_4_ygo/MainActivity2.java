package com.example.tcf_task5_4_ygo;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main2);

        //1
        TextView tv_card_name = findViewById(R.id.tv2_card_name);
        TextView tv_card_type = findViewById(R.id.tv2_card_type);
        TextView tv_card_image = findViewById(R.id.tv2_card_image);
        ImageView iv = findViewById(R.id.iv2_image);

        //2
        Intent it2 = getIntent();
        tv_card_name.setText(it2.getStringExtra("card_name"));
        tv_card_type.setText(it2.getStringExtra("card_type"));
        tv_card_image.setText(it2.getStringExtra("card_image"));
        Picasso.get().load(it2.getStringExtra("card_image")).into(iv);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intent home = new Intent(Intent.ACTION_MAIN);
        home.addCategory(Intent.CATEGORY_HOME);
        startActivity(home);
        finish();
        return super.onKeyDown(keyCode, event);
    }
}
