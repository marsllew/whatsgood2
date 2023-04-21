package edu.wm.cs.cs425.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassword extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button changeBttn;

    private EditText newPassword, currEmail, password;
    private TextView loginFail, newPassShort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        mAuth = FirebaseAuth.getInstance();
        changeBttn = findViewById(R.id.UpdatePasswordButton);
        newPassword = findViewById(R.id.newPassword);
        currEmail = findViewById(R.id.currEmail);
        password = findViewById(R.id.currentPassword);
        loginFail = findViewById(R.id.txtLoginFail);
        newPassShort = findViewById(R.id.txtNewPassShort);

        changeBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateEmail();
            }
        });

    }

    private void updateEmail () {
        String newPasswordText, emailText, pass;
        newPasswordText = newPassword.getText().toString();
        emailText = currEmail.getText().toString();
        pass =password.getText().toString();

        if (emailText.length() <= 0 || newPasswordText.length() <= 0 || pass.length() <= 0){
            Toast.makeText(getApplicationContext(),"Please Enter All Fields", Toast.LENGTH_LONG).show();
            return;
        }
        if (newPasswordText.length() <= 5){
            newPassShort.setVisibility(View.VISIBLE);
            return;
        }
        else {
            newPassShort.setVisibility(View.INVISIBLE);
        }

        mAuth.signInWithEmailAndPassword(emailText,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
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
                else{
                    loginFail.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(),"failed", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

}