package com.example.tcf_task4_6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    Button bt;

    MultiChoiceDialog mcd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        //1
        String[] list = new String[]{"羅成員","哥哥","心焦u"};
        tv = findViewById(R.id.tv_result);
        bt = findViewById(R.id.button);
        mcd = new MultiChoiceDialog(this,"最愛",list);

        //2
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mcd.showDialog(new MultiChoiceDialog.OnDialogSubmitListener() {
                    @Override
                    public void onSubmit(ArrayList<String> selectedItems) {
                        String strResult = selectedItems.stream().collect(Collectors.joining());
                        tv.setText(strResult);
                    }
                });
            }
        });
    }
}