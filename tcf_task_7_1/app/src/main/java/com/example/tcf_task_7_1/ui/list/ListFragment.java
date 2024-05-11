package com.example.tcf_task_7_1.ui.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tcf_task_7_1.PhoneData;
import com.example.tcf_task_7_1.R;

import java.util.ArrayList;

public class ListFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_list, container, false);
//        ListViewModel listViewModel = new ViewModelProvider(this).get(ListViewModel.class);
        ArrayList<PhoneData> list = new ArrayList<>();
        for(int i=0;i<20;i++){
            list.add(new PhoneData(String.format("item%02d", i), "9527"));
        }
        ArrayAdapter<PhoneData> ada = new ArrayAdapter<>(
          getContext(),
          android.R.layout.simple_list_item_1,
          list
        );
        ListView lv = v.findViewById(R.id.lv_list);
        lv.setAdapter(ada);

        setHasOptionsMenu(true);

        return v;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.option_list_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(getContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}
