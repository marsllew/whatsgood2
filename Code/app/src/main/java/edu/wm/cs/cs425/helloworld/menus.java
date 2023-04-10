package edu.wm.cs.cs425.helloworld;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;


public class menus extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menus, container, false);

        Webscraper menu_retriever = new Webscraper();
        try {
            menu_retriever.webscrape();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ReviewModel rvMenu = new ReviewModel("PIZZA", "Sadler");
        ArrayList<ReviewModel> rvList = new ArrayList<>();
        rvList.add(rvMenu);
        RecyclerView recyclerView = view.findViewById(R.id.menuRecycle);
        RVAdapter menuadapt = new RVAdapter(getContext(), rvList);
        LinearLayoutManager llmMenu = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llmMenu);
        recyclerView.setAdapter(menuadapt);

        // Inflate the layout for this fragment
        return view;
    }
}