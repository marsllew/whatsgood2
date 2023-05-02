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

    private FirebaseFirestore db;
    ArrayList<ReviewModel> rvList;
    RecyclerView recyclerView;
    RVAdapter menuadapt;
    LinearLayoutManager llmMenu;

    ArrayList<String> alreadyAdded;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reviews, container, false);
        // Inflate the layout for this fragment

        db = FirebaseFirestore.getInstance();
        rvList = new ArrayList<>();
        alreadyAdded = new ArrayList<>();
        recyclerView = view.findViewById(R.id.reviewRecycle);
        menuadapt = new RVAdapter(getContext(), rvList);
        llmMenu = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llmMenu);
        recyclerView.setAdapter(menuadapt);
        List<String> reviewedFoods = new ArrayList<>();
        db.collectionGroup("reviews").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot document : documents) {
                    String foodName = document.getString("food");
                    String locationName = document.getString("location");
                    String username = document.getString("username");
                    String calories = document.getString("calories");
                    reviewedFoods.add(foodName);
                    Log.d("retrieve", foodName + locationName + username);

                    //new code
                    ArrayList<String> beingServed = new ArrayList();
                    for (ReviewModel reviewModel : menuSingleton.getInstance().getArrayList()) {
                        Log.d("retrieve3", "Review model food: " + reviewModel.getFoodName());
                        if(reviewModel.getFoodName() != null) {
                            beingServed.add(reviewModel.getFoodName());
                        }
                    }
                    if(beingServed.contains(foodName)){
                        if(foodName != null) {
                            alreadyAdded.add(foodName);
                            rvList.add(new ReviewModel(foodName, locationName, calories));
                        }
                    }
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

    @Override
    public void onResume(){
        List<String> reviewedFoods = new ArrayList<>();
        db.collectionGroup("reviews").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot document : documents) {
                    String foodName = document.getString("food");
                    String locationName = document.getString("location");
                    String username = document.getString("username");
                    String calories = document.getString("calories");
                    reviewedFoods.add(foodName);
                    Log.d("retrieve", foodName + locationName + username);

                    //new code
                    ArrayList<String> beingServed = new ArrayList();
                    for (ReviewModel reviewModel : menuSingleton.getInstance().getArrayList()) {
                        Log.d("retrieve3", "Review model food: " + reviewModel.getFoodName());
                        if(reviewModel.getFoodName() != null) {
                            beingServed.add(reviewModel.getFoodName());
                        }
                    }
                    //if(reviewModel.getFoodName() != null) {
                        if (beingServed.contains(foodName) && !alreadyAdded.contains(foodName)) {
                            rvList.add(new ReviewModel(foodName, locationName, calories));
                        }
                    //}
                }
                menuadapt.notifyDataSetChanged();
            }
        });
        super.onResume();
    }
}