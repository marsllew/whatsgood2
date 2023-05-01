package edu.wm.cs.cs425.helloworld;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class reviews extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reviews, container, false);
        // Inflate the layout for this fragment

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //ReviewModel rvMenu = new ReviewModel("Salad", "Sadler", "0 Cals");
        ArrayList<ReviewModel> rvList = new ArrayList<>();
        //rvList.add(rvMenu);
        RecyclerView recyclerView = view.findViewById(R.id.reviewRecycle);
        RVAdapter menuadapt = new RVAdapter(getContext(), rvList);
        LinearLayoutManager llmMenu = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llmMenu);
        recyclerView.setAdapter(menuadapt);
        db.collectionGroup("reviews").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot document : documents) {
                    String foodName = document.getString("food");
                    String locationName = document.getString("location");
                    String username = document.getString("username");
                    String calories = document.getString("calories");
                    Log.d("retrieve", foodName + locationName + username);
                    rvList.add(new ReviewModel(foodName, locationName, calories));
                }
                menuadapt.notifyDataSetChanged();
            }
        });
        /***
        DocumentReference reviewsRef = db.collection("Reviews").document("Sadler");
        reviewsRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    Log.d("test1", "DocumentWorking" + documentSnapshot.getData());
                }
            }


            /***
            List<DocumentSnapshot> documents = query();
            for (DocumentSnapshot document : documents) {
                String foodName = document.getString("food");
                String locationName = document.getString("location");
                String username = document.getString("username");
                String userText = document.getString("text");
                Log.d("retrieve", foodName + locationName + username);
                //rvList.add(new ReviewDisplayModel(foodName, locationName, username, userText));
            }
            menuadapt.notifyDataSetChanged();

        });
        ***/

        return view;
    }
}