package edu.wm.cs.cs425.helloworld;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class menus extends Fragment {
    List<List> lists = new ArrayList<>();
    ArrayList<ReviewModel> rvList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menus, container, false);

        Webscraper menu_retriever = new Webscraper();
        try {
           lists = menu_retriever.webscrape();
        } catch (IOException e) {
            e.printStackTrace();
        }

        DateFormat dateFormat = new SimpleDateFormat("kkmm");
        Date date = new Date();
        int time = new Integer(dateFormat.format(date));

        List<String> now_foods;
        if (time < 1000) {
            now_foods = lists.get(0);
        }
        else if (time < 1500) {
            now_foods = lists.get(1);
        }
        else if (time < 2000) {
            now_foods = lists.get(2);
        }
        else now_foods = lists.get(3);

        for (String item : now_foods) {
            rvList.add(new ReviewModel(item, "Sadler"));
        }
        RecyclerView recyclerView = view.findViewById(R.id.menuRecycle);
        RVAdapter menuadapt = new RVAdapter(getContext(), rvList);
        LinearLayoutManager llmMenu = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llmMenu);
        recyclerView.setAdapter(menuadapt);
        // Inflate the layout for this fragment
        return view;
    }
}