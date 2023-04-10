package edu.wm.cs.cs425.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        TextView username =(TextView) findViewById(R.id.editTextTextPersonName);
        TextView password =(TextView) findViewById(R.id.editTextTextPassword);

        Button loginbtn = (Button) findViewById(R.id.loginButton);

        loginbtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginPage.this,MainActivity.class);
                startActivity(i);
            }
        });
        //work?
    }
}