package com.example.tcf_task4_6_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    Button bt;
    TextView tv;
    MultiChoiceDialog mcd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        //1
        bt = findViewById(R.id.button);
        tv = findViewById(R.id.tv_result);

        //2
        hobby[] hobbies = new hobby[]{
                new hobby("柔成員"),
                new hobby("雄文安"),
                new hobby("泰山")
        };
        mcd = new MultiChoiceDialog(
          this,
                hobbies,
                "唉呦老歌"
        );

        //3
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mcd.showDialog(new MultiChoiceDialog.OnSetSubmitListener() {
                    @Override
                    public void onSubmit(ArrayList<hobby> selectedItem) {
                        ArrayList<String> aa = new ArrayList<>();
                        for(int i=0;i<selectedItem.stream().count();i++){
                            aa.add(selectedItem.get(i).getName());
                        }
                        String ss = aa.stream().collect(Collectors.joining("+"));
                        tv.setText(ss);
                    }
                });
            }
        });

    }
}