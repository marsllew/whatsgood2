package edu.wm.cs.cs425.helloworld;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class dhmenu extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dhmenu, container, false);
        // Inflate the layout for this fragment


        DHModel dhModel = new DHModel("Sadler");
        DHModel dhModel1 = new DHModel("The Commons");
        ArrayList<DHModel> dhModelArrayList = new ArrayList<>();
        dhModelArrayList.add(dhModel);
        dhModelArrayList.add(dhModel1);
        RecyclerView recyclerView = view.findViewById(R.id.DH);
        DHRVAdapter dhmenuadapt = new DHRVAdapter(getContext(), dhModelArrayList);
        LinearLayoutManager llmMenu = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llmMenu);
        recyclerView.setAdapter(dhmenuadapt);

        return view;
    }

}