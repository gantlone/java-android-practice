package com.example.tcf_task_fragment.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavOptions;

import com.example.tcf_task_fragment.MainActivity;
import com.example.tcf_task_fragment.MainActivity2;
import com.example.tcf_task_fragment.Phone;
import com.example.tcf_task_fragment.R;
import com.example.tcf_task_fragment.databinding.FragmentHomeBinding;
import com.example.tcf_task_fragment.ui.PhoneLiveData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    ArrayList<Phone> list = new ArrayList<>();
    Phone item;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //1
        ListView lv = binding.listView;

        //1.5
        PhoneLiveData vm = new ViewModelProvider(getActivity()).get(PhoneLiveData.class);
        MutableLiveData<ArrayList<Phone>> listLiveData = vm.getListLiveData();
        MutableLiveData<Phone> itemLiveData = vm.getItemLiveData();

        //2
        list = listLiveData.getValue();
        if(list==null){
            list = new ArrayList<>();
            String[] first = {"羅","熊","泰"};
            String[] second = {"傑","成員","山"};
            Random rd = new Random();
            for(int i=0;i<30;i++){
                String name = first[rd.nextInt(3)] + second[rd.nextInt(3)];
                String phone = "09" + String.valueOf(rd.nextInt(100000000));
                int index = i;
                list.add(new Phone(name,phone,index));
            }
            listLiveData.postValue(list);
        }

        item = itemLiveData.getValue();
        if(item==null){
            itemLiveData.postValue(new Phone("","",0));
        }

        Bundle bundle = getArguments();
        if(bundle!=null){
            Phone pd = (Phone) bundle.getSerializable("phone");
            list.set(pd.getIndex(), pd);
        }


        //3
        ArrayAdapter<Phone> ada = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                list
        );

        //4
        lv.setAdapter(ada);
        setHasOptionsMenu(true);
        registerForContextMenu(lv);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                itemLiveData.postValue(list.get(i));
                NavOptions navOptions = new NavOptions.Builder().setPopUpTo(R.id.navigation_home, true).build();
                MainActivity.navController.navigate(R.id.navigation_dashboard,null,navOptions);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = requireActivity().getMenuInflater();
        menuInflater.inflate(R.menu.love_roger_roger, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        ContextMenu.ContextMenuInfo cmi = item.getMenuInfo();
        AdapterView.AdapterContextMenuInfo acm = (AdapterView.AdapterContextMenuInfo) cmi;
        int position = acm.position;
        Phone phone = list.get(position);
        switch (item.getItemId()){
            case R.id.home:
                NavOptions navOptions = new NavOptions.Builder().setPopUpTo(R.id.navigation_home, true).build();
                MainActivity.navController.navigate(R.id.navigation_notifications,null,navOptions);
                break;
            case R.id.roger:
                Toast.makeText(getContext(), "roger", Toast.LENGTH_SHORT).show();
                Intent it = new Intent(getContext(), MainActivity2.class);
                it.putExtra("phone", phone);
                startActivity(it);
                break;
            case R.id.nl:
                Toast.makeText(getContext(), "nl", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }
}