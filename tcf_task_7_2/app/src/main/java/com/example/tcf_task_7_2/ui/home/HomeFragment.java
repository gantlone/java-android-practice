package com.example.tcf_task_7_2.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavOptions;

import com.example.tcf_task_7_2.MainActivity;
import com.example.tcf_task_7_2.MainViewModel;
import com.example.tcf_task_7_2.PhoneData;
import com.example.tcf_task_7_2.R;
import com.example.tcf_task_7_2.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        binding.tv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                NavOptions navOptions = new NavOptions.Builder()
                        .setPopUpTo(R.id.navigation_home, true) // 确保将 Fragment A 从回退栈中移除
                        .build();
                MainActivity.navController.navigate(R.id.navigation_dashboard, null, navOptions);
                return true;
            }
        });

        //1
        ListView lv = binding.listView;

        //2
        //注意這
        MainViewModel mainViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
        MutableLiveData<PhoneData> listItemLiveData = mainViewModel.getListItem();
        MutableLiveData<ArrayList<PhoneData>> listLiveData = mainViewModel.getList();

        //3
        ArrayList<PhoneData> list = listLiveData.getValue();
        if(list==null){
            list = new ArrayList<>();
            for(int i=0;i<20;i++){
                list.add(new PhoneData(String.format( "item%02d", i), "1234", i));
            }
            listLiveData.postValue(list);
        }

        PhoneData listItem = listItemLiveData.getValue();
        if(listItem==null){
            listItem = new PhoneData("","",0);
            listItemLiveData.postValue(listItem);
        }

        //4
        Bundle bundle = getArguments();
        if(bundle!=null){
            PhoneData pd = (PhoneData) bundle.getSerializable("phone");
            list.set(pd.getIndex(), pd);
        }

        //5
        ArrayAdapter<PhoneData> ada = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                list
        );

        lv.setAdapter(ada);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PhoneData pd = listLiveData.getValue().get(i);
                listItemLiveData.postValue(pd);
                NavOptions navOptions = new NavOptions.Builder().setPopUpTo(R.id.navigation_home, true).build();
                MainActivity.navController.navigate(R.id.navigation_dashboard, null, navOptions);
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