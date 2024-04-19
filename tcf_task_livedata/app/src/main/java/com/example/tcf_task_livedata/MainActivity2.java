package com.example.tcf_task_livedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    private TextView textView;
    private TextView textView2;
    private Button bt;
    private Boolean boo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main2);

        textView = findViewById(R.id.textView2);
        textView2 = findViewById(R.id.textView22);
        bt = findViewById(R.id.button2);

        viewModel vm = new ViewModelProvider(this).get(viewModel.class);
        boo = vm.getLiveData().getValue();
        if(boo){
            textView2.setText("ok");
        }else{
            textView2.setText("no");
        }
        vm.setLiveDataValue(false);
        vm.getLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean value) {
                if (value) {
                    textView.setText("OK");
                }else{
                    textView.setText("no");
                }
            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity2.this,MainActivity.class);
                it.putExtra("bool",boo);
                startActivity(it);
            }
        });
    }
}