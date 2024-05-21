package com.example.tcf_task_tab_select.ui.dashboard;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavOptions;

import com.example.tcf_task_tab_select.Data;
import com.example.tcf_task_tab_select.MainActivity;
import com.example.tcf_task_tab_select.R;
import com.example.tcf_task_tab_select.databinding.MyMain2Binding;
import com.example.tcf_task_tab_select.liveData;

import java.util.Random;

public class DashboardFragment extends Fragment {

    private MyMain2Binding binding;
    private  Data d;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = MyMain2Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //1
        EditText et2 = binding.et2;
        EditText et22 = binding.et22;
        Button btn = binding.btn2;
        Random rd = new Random();
        Resources res = getResources();
        TypedArray colors = res.obtainTypedArray(R.array.color);
        Bundle bd = getArguments();
        if(bd!=null){
            d = (Data) bd.getSerializable("data");
            et2.setText(d.getName());
            et22.setText(d.getSex());
            et2.setTextColor(colors.getColor(rd.nextInt(4), Color.BLACK));
        }else{
            et2.setHint("名字");
            et22.setHint("性別");
            et2.setText("");
            et22.setText("");
        }

        //2
        liveData ld = new ViewModelProvider(getActivity()).get(liveData.class);
        MutableLiveData<Data> itemLiveData = ld.getItemLiveData();

        //3
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bb = new Bundle();
                d.setName(et2.getText().toString());
                d.setSex(et22.getText().toString());
                itemLiveData.postValue(d);
                bb.putSerializable("data", itemLiveData.getValue());

                NavOptions navOptions = new NavOptions.Builder().setPopUpTo(R.id.navigation_dashboard, true).build();
                MainActivity.navController.navigate(R.id.navigation_home,bb,navOptions);

            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}