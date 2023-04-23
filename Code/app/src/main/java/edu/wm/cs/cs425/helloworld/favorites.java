package edu.wm.cs.cs425.helloworld;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class favorites extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reviews, container, false);
        // Inflate the layout for this fragment


        ReviewModel rvMenu = new ReviewModel("Salad", "Sadler");
        ArrayList<ReviewModel> rvList = new ArrayList<>();
        rvList.add(rvMenu);
        RecyclerView recyclerView = view.findViewById(R.id.reviewRecycle);
        RVAdapter menuadapt = new RVAdapter(getContext(), rvList);
        LinearLayoutManager llmMenu = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llmMenu);
        recyclerView.setAdapter(menuadapt);

        return view;
    }

    private String getUserID (){
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        return currentUser.getUid();
    }

}