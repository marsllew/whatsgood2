package edu.wm.cs.cs425.helloworld;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
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

        submit.setOnClickListener(view -> {
            String text = reviewText.getText().toString();

            Review review = new Review(0, text);
            uploadReview(review);
        });
    }

    public void initializeViews() {
        reviewText = findViewById(R.id.inputText);
        submit = findViewById(R.id.submitButton);
    }

    public void uploadReview(Review review) {
        db.collection("Reviews")
                .document("Salad")
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

    private void getName(){
        user = FirebaseAuth.getInstance().getCurrentUser();
        Toast.makeText(getApplicationContext(),user.getDisplayName(), Toast.LENGTH_LONG).show();
    }

}