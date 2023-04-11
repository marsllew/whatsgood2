package edu.wm.cs.cs425.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

public class Leave_Review extends AppCompatActivity {

    private FirebaseFirestore db;
    private EditText reviewText;
    private Button submit;

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
                .set(review.getText());
    }
}