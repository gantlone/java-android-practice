package com.example.tcf_task_livedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private TextView textView1;
    private Button bt;
    private Boolean boo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        textView = findViewById(R.id.textView1);
        textView1 = findViewById(R.id.textView11);
        bt = findViewById(R.id.button1);

        viewModel vm = new ViewModelProvider(this).get(viewModel.class);
        boo = vm.getLiveData().getValue();
        if(boo){
            textView1.setText("ok");
        }else{
            textView1.setText("no");
        }
        vm.setLiveDataValue(true);
        vm.getLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean value) {
                if (!value) {
                    textView.setText("OK");
                }else{
                    textView.setText("no");
                }

            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, MainActivity2.class);
                it.putExtra("bool",boo);
                startActivity(it);
            }
        });

    }
}