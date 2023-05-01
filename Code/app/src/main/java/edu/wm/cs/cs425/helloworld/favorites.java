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

public class favorites extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        ArrayList<ReviewModel> rvList = new ArrayList<>();
        RecyclerView recyclerView = view.findViewById(R.id.favoritesRecycle);
        RVAdapter menuadapt = new RVAdapter(getContext(), rvList);
        LinearLayoutManager llmMenu = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llmMenu);
        recyclerView.setAdapter(menuadapt);
        CollectionReference favoritesRef = db.collection("users").document(getUserID()).collection("favorites");
        favoritesRef.get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot document : documents) {
                        String foodName = document.getString("foodname");
                        String locationName = document.getString("locationname");
                        String calories = document.getString("calories");
                        Log.d("retrieve", foodName + locationName + calories);
                        rvList.add(new ReviewModel(foodName, locationName, calories));
                        boolean isFavoriteFoodOnMenu = false;
                        List<String> favoriteFoods = getFavoriteFoods();
                        Log.d("retrieve", "Favorite food: " + favoriteFoods);
                        for (ReviewModel reviewModel : rvList) {
                            Log.d("retrieve", "Review model food: " + reviewModel.getFoodName());
                            if(reviewModel.getFoodName() != null) {
                                if (reviewModel.getFoodName().equals(favoriteFoods)) {
                                    Log.d("retrieve", "Review model food: " + reviewModel.getFoodName());
                                    isFavoriteFoodOnMenu = true;
                                    Log.d("retrieve", "Match found");
                                    break;
                                }
                            }
                        }


                        if (isFavoriteFoodOnMenu) {
                            Toast.makeText(getContext(), "One or more of your favorite foods are on the menu!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "None of your favorite foods are on the menu.", Toast.LENGTH_SHORT).show();
                        }

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

    private List<String> getFavoriteFoods() {
        List<String> favoriteFoods = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference favoritesRef = db.collection("users").document(getUserID()).collection("favorites");
        favoritesRef.get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot document : documents) {
                        String foodName = document.getString("foodname");
                        favoriteFoods.add(foodName);
                    }
                })
                .addOnFailureListener(e -> {
                    Log.w("Firestore", "could not retrieve data from database");
                });
        return favoriteFoods;
    }
}