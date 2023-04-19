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

public class ChangePassword extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button changeBttn;

    private EditText newPassword, oldEmail, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        mAuth = FirebaseAuth.getInstance();
        changeBttn = findViewById(R.id.UpdatePasswordButton);
        newPassword = findViewById(R.id.newPassword);
        oldEmail = findViewById(R.id.currEmail);
        password = findViewById(R.id.currentPassword);

        changeBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateEmail();
            }
        });

    }

    private void updateEmail () {
        String newPasswordText, oldEmailText, pass;
        newPasswordText = newPassword.getText().toString();
        oldEmailText = oldEmail.getText().toString();
        pass =password.getText().toString();
        mAuth.signInWithEmailAndPassword(oldEmailText,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    user.updatePassword(newPasswordText).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Intent changeSuc = new Intent(ChangePassword.this, LoginPage.class);
                                Toast.makeText(getApplicationContext(), "Password Updated", Toast.LENGTH_LONG).show();
                                startActivity(changeSuc);
                            }
                        }
                    });


                }
            }
        });


    }

}