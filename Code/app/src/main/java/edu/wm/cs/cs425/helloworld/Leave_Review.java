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
import com.google.firebase.firestore.FirebaseFirestore;

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

        TextView foodT = findViewById(R.id.Sampletxt1), locationT = findViewById(R.id.Sampletxt);
        foodT.setText(food);
        locationT.setText(location);

        ImageView image = findViewById(R.id.review_pic);
        image.setImageResource(pic);

        submit.setOnClickListener(view -> {
            String text = reviewText.getText().toString();

            Review review = new Review(rating, text, food, location);
            uploadReview(review);
        });
    }

    public void initializeViews() {
        reviewText = findViewById(R.id.inputText);
        submit = findViewById(R.id.submitButton);
    }

    public void uploadReview(Review review) {
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
    }

    private String getName(){
        user = FirebaseAuth.getInstance().getCurrentUser();
        Toast.makeText(getApplicationContext(),user.getDisplayName(), Toast.LENGTH_LONG).show();
        return user.getDisplayName();
    }

}