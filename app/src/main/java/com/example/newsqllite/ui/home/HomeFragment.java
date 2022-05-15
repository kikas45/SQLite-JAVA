package com.example.newsqllite.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsqllite.Adapters.CoffeADapter;
import com.example.newsqllite.Helpers.CoffeItem;
import com.example.newsqllite.R;


import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ArrayList<CoffeItem> coffeItems = new ArrayList<>();

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new CoffeADapter(coffeItems, getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        coffeItems.add(new CoffeItem(R.drawable.ic_launcher_background, "powell", "0","0"));
        coffeItems.add(new CoffeItem(R.drawable.ic_launcher_background, "John", "1","0"));
        coffeItems.add(new CoffeItem(R.drawable.ic_launcher_background, "Ray", "2","0"));
        coffeItems.add(new CoffeItem(R.drawable.ic_launcher_background, "james", "3","0"));

        return root;
    }


}