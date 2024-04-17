package com.example.tcf_task5_4_ygo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
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
        tv_card_name.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder bi = new AlertDialog.Builder(MainActivity2.this);
                bi.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                bi.setNegativeButton("取消",null);
                bi.setTitle("我愛羅盛垣");
                boolean[] aa = new boolean[]{false,false};
                String[] bb = new String[]{"aa","bb"};
                bi.setMultiChoiceItems(bb, aa, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {

                    }
                });
                bi.show();
                return true;
            }
        });
        tv_card_image.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PopupMenu pm = new PopupMenu(getApplicationContext(), view);
                pm.getMenuInflater().inflate(R.menu.opt_menu, pm.getMenu());
                pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.opt_all_Chk:
                                Log.d("123","123");
                                break;
                        }
                        return true;
                    }
                });
                pm.show();
                return true;
            }
        });

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
