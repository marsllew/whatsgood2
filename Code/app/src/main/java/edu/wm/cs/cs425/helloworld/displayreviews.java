package edu.wm.cs.cs425.helloworld;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class displayreviews extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_displayreviews, container, false);
        // Inflate the layout for this fragment


        ReviewDisplayModel reviewDisplayModel = new ReviewDisplayModel("Salad", "Sadler", "sample");
        ArrayList<ReviewDisplayModel> reviewDisplayModelArrayListList = new ArrayList<>();
        reviewDisplayModelArrayListList.add(reviewDisplayModel);
        RecyclerView recyclerView = view.findViewById(R.id.displayrv);
        DRRVAdapter menuadapt = new DRRVAdapter(getContext(), reviewDisplayModelArrayListList);
        LinearLayoutManager llmMenu = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llmMenu);
        recyclerView.setAdapter(menuadapt);

        return view;

    }
}