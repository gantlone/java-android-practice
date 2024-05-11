package com.example.tcf_task_7_1.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.tcf_task_7_1.MainActivity;
import com.example.tcf_task_7_1.R;
import com.example.tcf_task_7_1.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

//    private FragmentNotificationsBinding binding;
@Nullable
@Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        NotificationsViewModel notificationsViewModel =
//                new ViewModelProvider(this).get(NotificationsViewModel.class);
//
//        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//        final TextView textView = binding.textNotifications;
//        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
//        return root;

        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_notifications, container, false);
//        NotificationsViewModel notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);
        EditText et = v.findViewById(R.id.editTextTextPersonName);
        v.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity activity = (MainActivity) getActivity();

                NavController navController = activity.navController;

                navController.navigate(R.id.navigation_list);


            }
        });

        return v;
    }


}