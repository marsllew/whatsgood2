package edu.wm.cs.cs425.helloworld;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;


public class menus extends Fragment {
    Dictionary<String, List<String>> final_dict = new Hashtable<>();
    ArrayList<ReviewModel> rvList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menus, container, false);

        Webscraper menu_retriever = new Webscraper();
        try {
           final_dict = menu_retriever.webscrape();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Enumeration<String> k = final_dict.keys();
        while (k.hasMoreElements()) {
            String key = k.nextElement();
            for (String item : final_dict.get(key)) {
                rvList.add(new ReviewModel(item, key));
            }
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