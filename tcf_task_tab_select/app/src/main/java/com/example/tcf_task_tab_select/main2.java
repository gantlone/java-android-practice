package com.example.tcf_task_tab_select;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.tcf_task_tab_select.databinding.MaintwoBinding;

public class main2 extends AppCompatActivity {
    private MaintwoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = MaintwoBinding.inflate(getLayoutInflater());

        Intent it = getIntent();
        String name = it.getStringExtra("name");

        TextView tv = binding.tv;
        tv.setText(name);



    }
}
