package com.example.tcf_task_tab_select.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tcf_task_tab_select.Card;
import com.example.tcf_task_tab_select.MainActivity;
import com.example.tcf_task_tab_select.api;
import com.example.tcf_task_tab_select.databinding.FragmentNotificationsBinding;
import com.example.tcf_task_tab_select.databinding.MyMain3Binding;
import com.example.tcf_task_tab_select.main2;
import com.example.tcf_task_tab_select.recyclerVIewCard;

import org.json.JSONException;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private MyMain3Binding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = MyMain3Binding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //1
        RecyclerView rv = binding.rv;
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        //2
        String url = "https://db.ygoprodeck.com/api/v7/cardinfo.php?type=Normal%20Monster&num=20&offset=0";
        final String[] body = {""};
        final ArrayList<Card>[] list = new ArrayList[0];
        final ArrayList<Card>[] d = new ArrayList[]{new ArrayList<>()};
        Thread th_body= new Thread(new Runnable() {
            @Override
            public void run() {
                body[0] = api.api_json(url);
            }
        });
        th_body.start();
        try {
            th_body.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Thread th_list = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    d[0] = api.convert(body[0]);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        th_list.start();
        try {
            th_list.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        recyclerVIewCard ada = new recyclerVIewCard(getContext(), d[0]);

        ada.nn(new recyclerVIewCard.OnSetClickListener() {
            @Override
            public void OnSetClick(Card card, int position) {
                Intent it = new Intent(getContext(), main2.class);
                it.putExtra("name", card.getName());
                startActivity(it);
            }
        });

        rv.setAdapter(ada);



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}