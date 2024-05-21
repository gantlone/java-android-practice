package com.example.tcf_task_tab_select.ui.home;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavOptions;

import com.example.tcf_task_tab_select.Data;
import com.example.tcf_task_tab_select.MainActivity;
import com.example.tcf_task_tab_select.R;
import com.example.tcf_task_tab_select.databinding.MyMainBinding;
import com.example.tcf_task_tab_select.liveData;

import java.util.ArrayList;
import java.util.Random;

public class HomeFragment extends Fragment {

    private MyMainBinding binding;

    private ArrayList<Data> list;
    private Data item;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = MyMainBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //1
        ListView lv = binding.lv;
        Resources res = getResources();
        String[] name = res.getStringArray(R.array.name);
        String[] sex = {"girl", "boy"};
        Random rd = new Random();

        //2
        liveData ld = new ViewModelProvider(getActivity()).get(liveData.class);
        MutableLiveData<ArrayList<Data>> listLiveData = ld.getListLiveData();
        MutableLiveData<Data> itemLiveData = ld.getItemLiveData();

        //3
        list = listLiveData.getValue();
        if(list==null){
            list = new ArrayList<>();
            for(int i=0;i<name.length;i++){
                String model_name = name[i];
                int model_index = i;
                String model_sex = sex[rd.nextInt(2)];

                list.add(new Data(model_name, model_index, model_sex));
            }

            listLiveData.postValue(list);
        }

        item = itemLiveData.getValue();
        if(item==null){
            item = list.get(0);

            itemLiveData.postValue(item);
        }

        Bundle bd = getArguments();
        if(bd!=null){
            Data d = (Data) bd.getSerializable("data");
            list.set(d.getIndex(),d);
            listLiveData.postValue(list);
        }

        ArrayAdapter<Data> ada = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, list);

        lv.setAdapter(ada);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bb = new Bundle();
                bb.putSerializable("data", listLiveData.getValue().get(i));
                NavOptions navOptions = new NavOptions.Builder().setPopUpTo(R.id.navigation_home, true).build();
                MainActivity.navController.navigate(R.id.navigation_dashboard, bb, navOptions);
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