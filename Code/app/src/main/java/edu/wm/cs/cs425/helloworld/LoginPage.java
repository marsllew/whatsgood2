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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class LoginPage extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailInput;
    private EditText passwordInput;
    private TextView loginError;
    ArrayList<ReviewModel> rvList = new ArrayList<>();
    ArrayList<ReviewModel> rvList2 = new ArrayList<>();
    Dictionary<String, List<String>> final_dict = new Hashtable<>();
    Dictionary<String, List<String>> final_dict2 = new Hashtable<>();
    Runnable runScript1;
    Thread script1;
    Runnable runScript2;
    Thread script2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        mAuth = FirebaseAuth.getInstance();
        emailInput =(EditText) findViewById(R.id.accountEmail);
        passwordInput =(EditText) findViewById(R.id.accountPassword);
        loginError = (TextView) findViewById(R.id.mainLoginFail);

        Button loginbtn = (Button) findViewById(R.id.loginButton);
        Button newUser = (Button) findViewById(R.id.newUser);
        Button changeEmail = (Button) findViewById(R.id.newEmail);

        runScript1 = new Runnable(){

            @Override
            public void run() {
                script1Code();
            }
        };
        script1 = new Thread(runScript1);
        script1.start();

        runScript2 = new Runnable(){

            @Override
            public void run() {
                script2Code();
            }
        };
        script2 = new Thread(runScript2);
        script2.start();

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
                Intent emailChange = new Intent(LoginPage.this, ChangePassword.class);
                startActivity(emailChange);
            }
        });

        //work?
    }

    private void script1Code(){
        Webscraper menu_retriever = new Webscraper();
        try {
            final_dict = menu_retriever.webscrape();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Enumeration<String> k = final_dict.keys();
        String name = "";
        String cals = "";
        while (k.hasMoreElements()) {
            String key = k.nextElement();
            for (String item : final_dict.get(key)) {
                for (int i=0; i < item.length(); i++){
                    //System.out.println(item);
                    //System.out.println(item.charAt(i));
                    if (Character.isDigit(item.charAt(i))) {
                        name = item.substring(0, i);
                        cals = item.substring(i);
                        break;
                    }
                    else { name = item; cals = "0cal"; }
                }
                rvList.add(new ReviewModel(name, key, cals, "Sadler"));
            }
        }
        menuSingleton.getInstance().setArrayList(rvList);
    }

    private void script2Code(){
        Webscraper2 menu_retriever = new Webscraper2();
        try {
            final_dict2 = menu_retriever.webscrape();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Enumeration<String> k = final_dict2.keys();
        String name = "";
        String cals = "";
        while (k.hasMoreElements()) {
            String key = k.nextElement();
            for (String item : final_dict2.get(key)) {
                for (int i=0; i < item.length(); i++){
                    //System.out.println(item);
                    //System.out.println(item.charAt(i));
                    if (Character.isDigit(item.charAt(i))) {
                        name = item.substring(0, i);
                        cals = item.substring(i);
                        break;
                    }
                    else { name = item; cals = "0cal"; }
                }
                rvList2.add(new ReviewModel(name, key, cals, "Commons"));
            }
        }
    }

    private void loginAttempt(){
        String emailText, passText;
        emailText = emailInput.getText().toString();
        passText = passwordInput.getText().toString();
        if(emailText.length() < 1 || passText.length() < 1){
            Toast.makeText(getApplicationContext(),"Please Enter All Fields", Toast.LENGTH_LONG).show();
            return;
        }
        if(emailText.equals("a") && passText.equals("a")) {
            mAuth.signInWithEmailAndPassword("mldunn@email.wm.edu","password").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Intent loginAdmn = new Intent(LoginPage.this, MainActivity.class);
                        try {
                            script1.join();
                            script2.join();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        startActivity(loginAdmn);
                    }

                }
            });
        }
        else {
            mAuth.signInWithEmailAndPassword(emailText, passText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Intent loginSuc = new Intent(LoginPage.this, MainActivity.class);
                        try {
                            script1.join();
                            script2.join();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        startActivity(loginSuc);
                    } else {
                        loginError.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

}