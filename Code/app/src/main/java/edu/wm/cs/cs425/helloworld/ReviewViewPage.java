package edu.wm.cs.cs425.helloworld;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ReviewViewPage extends AppCompatActivity {

    private TextView foodTitle, locationTitle, avgRating;
    private Double oldRating;
    private Double newRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_view_page);

        Bundle extras = getIntent().getExtras();
        String food = extras.getString("food");
        String location = extras.getString("location");
        double rating = (double) extras.getInt("rating");
        foodTitle = findViewById(R.id.Foodname);
        locationTitle = findViewById(R.id.locationname);
        avgRating = findViewById(R.id.avgRating);
        foodTitle.setText(food);
        locationTitle.setText(location);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //ReviewDisplayModel reviewDisplayModel = new ReviewDisplayModel(food, location, getUserID());
        ArrayList<ReviewDisplayModel> reviewDisplayModelArrayListList = new ArrayList<>();
        //reviewDisplayModelArrayListList.add(reviewDisplayModel);
        RecyclerView recyclerView = findViewById(R.id.displayrv);
        DRRVAdapter menuadapt = new DRRVAdapter(this, reviewDisplayModelArrayListList);
        LinearLayoutManager llmMenu = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llmMenu);
        recyclerView.setAdapter(menuadapt);
        CollectionReference reviewsRef = db.collection("Reviews").document("Sadler").collection(location).document(food).collection("reviews");
        reviewsRef.get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
            for (DocumentSnapshot document : documents) {
                String foodName = document.getString("food");
                String locationName = document.getString("location");
                String username = document.getString("username");
                String userText = document.getString("text");
                double userRating = document.getDouble("rating");
                Log.d("retrieve", foodName + locationName + username);
                reviewDisplayModelArrayListList.add(new ReviewDisplayModel(foodName, locationName, username, userText, userRating));
            }
            menuadapt.notifyDataSetChanged();
        });
        DocumentReference rateRef = db.collection("Reviews")
                .document("Sadler")
                .collection(location)
                .document(food);
        rateRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    Log.d(TAG, "document retrieved");
                    if (document.exists()) {
                        if (document.contains("rating")) {
                            oldRating = (double) document.get("rating");
                            Log.d(TAG, String.valueOf(oldRating));
                            avgRating.setText("Rating: " + oldRating);
                        }
                    }
                }
            }
        });

                /***.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    QuerySnapshot collection = task.getResult();
                    Log.d("testing10","doc no exist");
                    if (collection.exists()){
                        Log.d("testing4", "random Test");
                        Log.d("testing1", "DocumentSnapshot data: " + collection.getData());
                    }
                } else{
                    Log.d("testing3", "Document Failed");
                }
            }
        });
        ***/

    }

    private String getUserID(){
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        return currentUser.getUid();
    }
}