package com.example.tcf_task_fragment.ui.notifications;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tcf_task_fragment.R;
import com.example.tcf_task_fragment.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //1
        TextView tv = binding.textView2;
        tv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PopupMenu pm = new PopupMenu(getContext(), view);
                pm.getMenuInflater().inflate(R.menu.love_roger_roger, pm.getMenu());
                pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.nl:
                                Toast.makeText(getContext(), "123", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.roger:
                                AlertDialog.Builder bi = new AlertDialog.Builder(getContext());
                                bi.setTitle("我愛羅盛垣");
                                String[] item = {"泰山","海報均","米特阿姨"};
                                boolean[] bool = {false,false,false};
                                bi.setMultiChoiceItems(item, bool, new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                        bool[i]=b;
                                    }
                                });
                                bi.setNegativeButton("估掰",null);
                                bi.setPositiveButton("帥哥登場", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        String ans="";
                                        for(int h=0;h<item.length;h++){
                                            if(bool[h]==true){
                                                ans += item[h] + " ";
                                            }
                                        }
                                        Toast.makeText(getContext(), ans, Toast.LENGTH_SHORT).show();
                                    }
                                });
                                bi.show();
                                break;
                        }
                        return false;
                    }
                });
                pm.show();
                return false;
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