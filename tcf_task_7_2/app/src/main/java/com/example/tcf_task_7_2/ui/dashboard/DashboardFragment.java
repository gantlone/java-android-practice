package com.example.tcf_task_7_2.ui.dashboard;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.example.tcf_task_7_2.MainActivity;
import com.example.tcf_task_7_2.MainViewModel;
import com.example.tcf_task_7_2.PhoneData;
import com.example.tcf_task_7_2.R;
import com.example.tcf_task_7_2.databinding.FragmentDashboardBinding;

import java.io.Serializable;
import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //1
        EditText et = binding.etName;
        Button btn = binding.btnBack;

        //2
        MainViewModel mainViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
        MutableLiveData<PhoneData> listItemLiveData = mainViewModel.getListItem();
        MutableLiveData<ArrayList<PhoneData>> listLiveData = mainViewModel.getList();

        PhoneData pd = listItemLiveData.getValue();

        //3
        et.setText(pd.getName());
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                pd.setName(editable.toString());
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("phone", pd);
                MainActivity activity = (MainActivity) getActivity();
                //强制转换成MainActivity，从MainActivity实例中获得NavController对象
                NavController navController = activity.navController;
                //MainActivity需要做相应修改，将NavController对象设为public成员变量
                navController.navigate(R.id.navigation_home,bundle);
                //导航并跳转至R.id.navigation_list指向的Fragment
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