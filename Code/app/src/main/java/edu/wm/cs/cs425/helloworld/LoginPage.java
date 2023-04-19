package edu.wm.cs.cs425.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextView emailInput;
    private TextView passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        mAuth = FirebaseAuth.getInstance();
        emailInput =(TextView) findViewById(R.id.accountEmail);
        passwordInput =(TextView) findViewById(R.id.accountPassword);

        Button loginbtn = (Button) findViewById(R.id.loginButton);
        Button newUser = (Button) findViewById(R.id.newUser);
        Button changeEmail = (Button) findViewById(R.id.newEmail);

        loginbtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                loginAttempt();

            }
        });
        newUser.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent reg = new Intent(LoginPage.this,LoginRegistration.class);
                startActivity(reg);
            }
        });

        changeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailChange = new Intent(LoginPage.this, ChangeEmail.class);
                startActivity(emailChange);
            }
        });

        //work?
    }

    private void loginAttempt(){
        String emailText, passText;
        emailText = emailInput.getText().toString();
        passText = passwordInput.getText().toString();
        if(emailText.equals("a") && passText.equals("a")) {
            Intent lgn = new Intent(LoginPage.this, MainActivity.class);
            startActivity(lgn);
        }

        mAuth.signInWithEmailAndPassword(emailText,passText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent loginSuc = new Intent(LoginPage.this, MainActivity.class);
                    startActivity(loginSuc);
                }
            }
        });
    }

}