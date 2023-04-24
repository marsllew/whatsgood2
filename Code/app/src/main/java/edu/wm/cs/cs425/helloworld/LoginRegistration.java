package edu.wm.cs.cs425.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class LoginRegistration extends AppCompatActivity {

    private EditText emailText, passwordText, usernameText;
    private TextView passShort, emailInvalid;
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
        passShort = findViewById(R.id.txtNewPassShort);
        emailInvalid = findViewById(R.id.txtLoginFail);
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
        if (newEmail.length() <= 0 || newPass.length() <= 0 || newName.length() <= 0){
            Toast.makeText(getApplicationContext(),"Please Enter All Fields", Toast.LENGTH_LONG).show();
            return;
        }
        if (newPass.length() <= 5){
            passShort.setVisibility(View.VISIBLE);
            return;
        }
        else {
            passShort.setVisibility(View.INVISIBLE);
        }
        mAuth.createUserWithEmailAndPassword(newEmail, newPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    UserProfileChangeRequest nameUpdate = new UserProfileChangeRequest.Builder().setDisplayName(newName).build();
                    user.updateProfile(nameUpdate);
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    String uid = user.getUid();
                    DocumentReference userRef = db.collection("users").document(uid);
                    Map<String, Object> userData = new HashMap<>();
                    userData.put("username", newName);
                    userData.put("email", newEmail);
                    userRef.set(userData).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d("UserAdded", "User has been added to Firestore");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("ErrorAddingUser", "Could not add user to Firestore", e);
                        }
                    });
                    Intent login = new Intent(LoginRegistration.this, MainActivity.class);
                    startActivity(login);

                }
                else{
                    emailInvalid.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(),"failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}