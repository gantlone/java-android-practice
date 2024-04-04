package com.example.tcf_task3_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Spinner sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);
        sp = findViewById(R.id.spinner);
        TextView tv = findViewById(R.id.textView);

        //1
        Resources res = getResources();
        String[] names = res.getStringArray(R.array.my_dimen_names);
        TypedArray values = res.obtainTypedArray(R.array.my_dimen_values);

        //2
//        ArrayAdapter ada = new ArrayAdapter<>(
//                this,
//                android.R.layout.simple_list_item_1,
//                names
//        );
        ArrayAdapter<CharSequence> ada = ArrayAdapter.createFromResource(
                this,
                R.array.my_dimen_names,
                android.R.layout.simple_list_item_1
        );

        //3
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                float dimen = values.getDimension(i,12f);
                Log.d("dimension", ""+dimen);
                Log.e("dimension", ""+dimen);
                Log.i("dimension", ""+dimen);
                Log.w("dimension", ""+dimen);
                tv.setTextSize(dimen);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //4
        sp.setAdapter(ada);
    }
}