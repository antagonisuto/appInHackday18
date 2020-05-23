package com.example.edil.firebaseapp;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailET;
    private EditText passwordET;
    private Button goRegister;
    private Button loginBtn;
    private ProgressBar progressBar;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        emailET = (EditText) findViewById(R.id.register_email);
        passwordET = (EditText) findViewById(R.id.register_password);
        goRegister = (Button) findViewById(R.id.login_reg);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        progressBar = (ProgressBar) findViewById(R.id.loginPrograss);








        goRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

            }
        });


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkLogin();

            }
        });

    }





    private void checkLogin() {

        String email = emailET.getText().toString().trim();
        String password = passwordET.getText().toString().trim();

        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){

            progressBar.setVisibility(View.VISIBLE);

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){

                        sendToNav();

                    }else{

                        String errorMessage =task.getException().getMessage();
                        Toast.makeText(LoginActivity.this,"Error :" + errorMessage,Toast.LENGTH_LONG).show();

                    }

                    progressBar.setVisibility(View.INVISIBLE);

                }

            });

        }
        else {
            Toast.makeText(LoginActivity.this,"Invalid Login",Toast.LENGTH_LONG).show();
        }
    }



    @Override
    protected void onStart() {

        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//            sendToMain();
//        }

        if(currentUser != null){
            sendToNav();
        }


    }

    private void sendToNav() {

        startActivity(new Intent(LoginActivity.this,NavActivity.class));

        finish();
    }


}
