package edu.wm.cs.cs425.helloworld;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Leave_Review extends AppCompatActivity {

    private FirebaseFirestore db;
    private EditText reviewText;
    private Button submit;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_review);

        initializeViews();
        db = FirebaseFirestore.getInstance();

        Bundle extras = getIntent().getExtras();
        String food = extras.getString("food");
        String location = extras.getString("location");
        int rating = extras.getInt("rating");
        int pic = extras.getInt("image");
        String corlocation = location.replace("/", " or ");
        Log.d(TAG, corlocation);
        TextView foodT = findViewById(R.id.Sampletxt1), locationT = findViewById(R.id.Sampletxt);
        foodT.setText(food);
        locationT.setText(corlocation);

        ImageView image = findViewById(R.id.review_pic);
        image.setImageResource(R.drawable.food);

        submit.setOnClickListener(view -> {
            String text = reviewText.getText().toString();

            Review review = new Review(rating, text, food, corlocation);
            uploadReview(review);
            //problem
        });
    }

    public void initializeViews() {
        reviewText = findViewById(R.id.inputText);
        submit = findViewById(R.id.submitButton);
    }

    public void uploadReview(Review review) {
        Map<String, Object> foodReview = new HashMap<>();
        foodReview.put("location", review.getLocation());
        foodReview.put("food", review.getFood());
        foodReview.put("username", getName());
        foodReview.put("text", review.getText());
        foodReview.put("rating", review.getRating());

        db.collection("Reviews")
                .document("Sadler")
                .collection(review.getLocation())
                .document(review.getFood())
                .collection("reviews")
                .document(user.getUid())
                .set(foodReview)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                });

        Map<String, Object> ratingStore = new HashMap<>();
        ratingStore.put("rating", review.getRating());
        db.collection("Reviews")
                .document("Sadler")
                .collection(review.getLocation())
                .document(review.getFood())
                .set(ratingStore)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                });


        /***
        db.collection("Reviews")
                .document(review.getLocation())
                .collection(review.getFood())
                .document(user.getUid())
                .set(review.getText())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
         ***/
    }

    private String getName(){
        user = FirebaseAuth.getInstance().getCurrentUser();
        Toast.makeText(getApplicationContext(),user.getDisplayName(), Toast.LENGTH_LONG).show();
        return user.getDisplayName();
    }

}