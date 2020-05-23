package com.example.edil.firebaseapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RegisterActivity extends AppCompatActivity {

    private Button registerBtn;
    private EditText registerEmail;
    private EditText registerPassword;
    private EditText registerConfirm;
    private ProgressBar registerProgress;
    private TextView informText;
    private EditText regiterNumber;
    private Button sendMessageBtn;
    private EditText registerCodeConfr;

    private FirebaseFirestore db;

    private static final String TAG = "PhoneAuth";
    private String phoneVerificationID;
    private String user_id;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks verificationCallBack;
    private PhoneAuthProvider.ForceResendingToken resendingToken;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        informText = findViewById(R.id.informationText);
        registerBtn = (Button) findViewById(R.id.registerBtn);
        registerEmail = (EditText) findViewById(R.id.register_email);
        registerPassword = (EditText) findViewById(R.id.register_password);
        registerConfirm = (EditText) findViewById(R.id.register_ConfiemPassword);

        //kuka code
        regiterNumber = findViewById(R.id.phoneNumber);
        sendMessageBtn = findViewById(R.id.sendMessageToPhone);
        registerCodeConfr = findViewById(R.id.conformationCode);

        sendMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCode(v);
            }
        });



        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = registerEmail.getText().toString();
                String password = registerPassword.getText().toString();
                String passConfirm = registerConfirm.getText().toString();
                final String telnumber = regiterNumber.getText().toString();
                final View view = v;

                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(passConfirm)){

                    if(password.equals(passConfirm)){



                        registerProgress.setVisibility(View.VISIBLE);

                        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){
                                    verifyCode(view);
                                    user_id = mAuth.getCurrentUser().getUid();
                                    Map<String, Object> user_tel = new HashMap<>();
                                    user_tel.put("tel", telnumber);

                                    db.collection("users")
                                            .document(user_id)
                                            .set(user_tel).addOnSuccessListener(new OnSuccessListener<Void>() {

                                        @Override
                                        public void onSuccess(Void aVoid) {

                                                Log.d(TAG, "DocumentSnapshot successfully written!");

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w(TAG, "Error writing document", e);
                                        }
                                    });

                                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                    finish();

                                } else {

                                    String erroMessage = task.getException().getMessage();
                                    Toast.makeText(RegisterActivity.this,"Error : " + erroMessage,Toast.LENGTH_LONG).show();

                                }

                                registerProgress.setVisibility(View.INVISIBLE);

                            }
                        });

                    }else{

                        Toast.makeText(RegisterActivity.this,"Please confirm your passowrd.",Toast.LENGTH_LONG).show();

                    }
                }
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            sendToMain();
        }
    }

    private void sendToMain() {
        Intent mainIntent = new Intent(RegisterActivity.this,MainActivity.class);
        startActivity(mainIntent);
        finish();
    }


    //kuka


    private void sendCode(View view){
        String phoneNumber = regiterNumber.getText().toString();
        setUpVerCallBack();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber,60, TimeUnit.SECONDS,this,verificationCallBack);
        informText.setVisibility(View.VISIBLE);
    }

    public void verifyCode(View view) {

        String code = registerCodeConfr.getText().toString();

        PhoneAuthCredential credential =
                PhoneAuthProvider.getCredential(phoneVerificationID, code);
        signInWithPhoneAuthCredential(credential);
    }


    private void setUpVerCallBack(){
        verificationCallBack=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    Log.d(TAG, "Invalid credential: "
                            + e.getLocalizedMessage());
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // SMS quota exceeded
                    Log.d(TAG, "SMS Quota exceeded.");
                }
            }

            @Override
            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                phoneVerificationID=verificationId;
                resendingToken=forceResendingToken;
            }
        };
    }




    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            registerCodeConfr.setText("");
                            FirebaseUser user = task.getResult().getUser();

                        } else {
                            if (task.getException() instanceof
                                    FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }
}
