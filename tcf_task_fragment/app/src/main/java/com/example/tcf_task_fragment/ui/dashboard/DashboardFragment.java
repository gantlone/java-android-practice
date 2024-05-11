package com.example.tcf_task_fragment.ui.dashboard;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavOptions;

import com.example.tcf_task_fragment.MainActivity;
import com.example.tcf_task_fragment.Phone;
import com.example.tcf_task_fragment.R;
import com.example.tcf_task_fragment.databinding.FragmentDashboardBinding;
import com.example.tcf_task_fragment.ui.PhoneLiveData;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setHasOptionsMenu(true);

        //1
        EditText et = binding.editTextTextPersonName;
        Button btn = binding.button;

        //1.5
        PhoneLiveData vm = new ViewModelProvider(getActivity()).get(PhoneLiveData.class);
        MutableLiveData<ArrayList<Phone>> listLiveData = vm.getListLiveData();
        MutableLiveData<Phone> itemLiveData = vm.getItemLiveData();

        //2
        Phone pd = itemLiveData.getValue();
        et.setText(pd.getName());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                pd.setName(et.getText().toString());
                bundle.putSerializable("phone",pd);
                NavOptions navOptions = new NavOptions.Builder().setPopUpTo(R.id.navigation_dashboard,true).build();
                MainActivity.navController.navigate(R.id.navigation_home,bundle, navOptions);
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
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.love_roger_roger,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                NavOptions navOptions = new NavOptions.Builder().setPopUpTo(R.id.navigation_dashboard, true).build();
                MainActivity.navController.navigate(R.id.navigation_notifications,null,navOptions);
                break;
            case R.id.roger:
                Toast.makeText(getContext(), "roger", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nl:
                Toast.makeText(getContext(), "nl", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}