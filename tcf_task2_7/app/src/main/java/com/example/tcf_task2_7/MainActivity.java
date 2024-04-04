package com.example.tcf_task2_7;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);
        TextView tv = findViewById(R.id.textView);
        for(int i=1;i<4;i++){
            int finalI = i;
            findViewById(getResources().getIdentifier("button"+i,"id",getPackageName())).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int cr = Color.BLACK;
                    switch (finalI){
                        case 1:
                            cr = Color.BLUE;
                            break;
                        case 2:
                            cr = Color.YELLOW;
                            break;
                        case 3:
                            cr = Color.DKGRAY;
                            break;
                    }
                    tv.setTextColor(cr);
                }
            });

        }
    }
}