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

public class LoginRegistration extends AppCompatActivity {

    private EditText emailText, passwordText, usernameText;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_registration);

        //sets the firebase authority
        mAuth = FirebaseAuth.getInstance();

        emailText = findViewById(R.id.newEmail);
        passwordText = findViewById(R.id.newPassword);
        usernameText = findViewById(R.id.newUsername);
        Button regFinish = findViewById(R.id.newLoginReg);

        regFinish.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                newUserAccount();
            }
        });

    }

    //Creates a new user account if the user inputs a valid email and a password
    private void newUserAccount()
    {
        String newEmail = emailText.getText().toString();
        String newPass = passwordText.getText().toString();
        String newName = usernameText.getText().toString();


        mAuth.createUserWithEmailAndPassword(newEmail, newPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    UserProfileChangeRequest nameUpdate = new UserProfileChangeRequest.Builder().setDisplayName(newName).build();
                    user.updateProfile(nameUpdate).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(getApplicationContext(),"NameUpdate", Toast.LENGTH_LONG).show();
                        }
                    });
                    Toast.makeText(getApplicationContext(),"worked", Toast.LENGTH_LONG).show();
                    Intent login = new Intent(LoginRegistration.this, MainActivity.class);
                    startActivity(login);

                }
                else{
                    Toast.makeText(getApplicationContext(),"failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}