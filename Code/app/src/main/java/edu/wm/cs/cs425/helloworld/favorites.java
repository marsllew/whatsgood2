package edu.wm.cs.cs425.helloworld;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class favorites extends Fragment {

    private FirebaseFirestore db;
    private ArrayList<ReviewModel> rvList;
    private RecyclerView recyclerView;
    private RVAdapter menuadapt;
    private ArrayList<String> favoriteFoods;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        db = FirebaseFirestore.getInstance();
        rvList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.favoritesRecycle);
        menuadapt = new RVAdapter(getContext(), rvList);
        LinearLayoutManager llmMenu = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llmMenu);
        recyclerView.setAdapter(menuadapt);
        favoriteFoods = new ArrayList<>();
        AtomicBoolean isFavoriteFoodOnMenu = new AtomicBoolean(false);
        CollectionReference favoritesRef = db.collection("users").document(getUserID()).collection("favorites");
        favoritesRef.get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot document : documents) {
                        String foodName = document.getString("foodname");
                        String locationName = document.getString("locationname");
                        String calories = document.getString("calories");
                        String diningHall = document.getString("diningHall");
                        favoriteFoods.add(foodName);
                        //Log.d("retrieve1", foodName + locationName + calories);
                        rvList.add(new ReviewModel(foodName, locationName, calories, diningHall));
                        isFavoriteFoodOnMenu.set(false);

                        for (ReviewModel reviewModel : menuSingleton.getInstance().getArrayList()) {
                            //Log.d("retrieve3", "Review model food: " + reviewModel.getFoodName());
                            if(reviewModel.getFoodName() != null) {
                                for(String foodItem: favoriteFoods) {
                                    if (reviewModel.getFoodName().equals(foodItem)) {
                                        //Log.d("retrieve4", "Review model food: " + reviewModel.getFoodName());
                                        isFavoriteFoodOnMenu.set(true);
                                        //Log.d("retrieve5", "Match found");
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (isFavoriteFoodOnMenu.get()) {
                        Toast.makeText(getContext(), "One or more of your favorite foods are on the menu!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "None of your favorite foods are on the menu.", Toast.LENGTH_SHORT).show();
                    }
                    menuadapt.notifyDataSetChanged();

                })
                .addOnFailureListener(e -> {
                    Log.w("Firestore", "could not retrieve data from database");
                });

        return view;
    }

    private String getUserID (){
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        return currentUser.getUid();
    }

    private List<String> getFavoriteFoods(List<String> favoriteFoods) {

        return favoriteFoods;
    }
    @Override
    public void onResume() {
        AtomicBoolean isFavoriteFoodOnMenu = new AtomicBoolean(false);
        CollectionReference favoritesRef = db.collection("users").document(getUserID()).collection("favorites");
        favoritesRef.get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot document : documents) {
                        String foodName = document.getString("foodname");
                        String locationName = document.getString("locationname");
                        String calories = document.getString("calories");
                        String diningHall = document.getString("diningHall");
                        if(!favoriteFoods.contains(foodName)){
                            favoriteFoods.add(foodName);
                            //Log.d("retrieve1", foodName + locationName + calories);
                            rvList.add(new ReviewModel(foodName, locationName, calories, diningHall));
                        }

                    }
                    menuadapt.notifyDataSetChanged();

                })
                .addOnFailureListener(e -> {
                    Log.w("Firestore", "could not retrieve data from database");
                });
        super.onResume();
    }
}