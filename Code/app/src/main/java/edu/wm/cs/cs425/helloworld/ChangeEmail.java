package edu.wm.cs.cs425.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class ChangeEmail extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button changeBttn;

    private EditText newEmail, oldEmail, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);
        mAuth = FirebaseAuth.getInstance();
        changeBttn = findViewById(R.id.UpdateEmailButton);
        newEmail = findViewById(R.id.changeEmailAdd);
        oldEmail = findViewById(R.id.oldEmail);
        password = findViewById(R.id.currentPassword);

        changeBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateEmail();
            }
        });

    }

    private void updateEmail () {
        String newEmailText, oldEmailText, pass;
        newEmailText = newEmail.getText().toString();
        oldEmailText = oldEmail.getText().toString();
        pass =password.getText().toString();
        mAuth.signInWithEmailAndPassword(oldEmailText,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    user.updateEmail(newEmailText).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Intent changeSuc = new Intent(ChangeEmail.this, LoginPage.class);
                                Toast.makeText(getApplicationContext(), "emailUpdated", Toast.LENGTH_LONG).show();
                                startActivity(changeSuc);
                            }
                        }
                    });


                }
            }
        });


    }

}