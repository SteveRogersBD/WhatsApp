package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.whatsapp.Models.Users;
import com.example.whatsapp.databinding.ActivityMainBinding;
import com.example.whatsapp.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase database;
    ActivitySignUpBinding binding;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(SignUpActivity.this);
        progressDialog.setTitle("Creating account");
        progressDialog.setMessage("We are creating your account");

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                String email=binding.emailEt.getText().toString();
                String password=binding.pwEt.getText().toString();

                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()) {
                                    //to save the user in my realtime database
                                    Users user = new Users(binding.usernameEt.getText().toString(),
                                            email,password);
                                    String id = task.getResult().getUser().getUid();
                                    database.getReference().child("Users").child(id).setValue(user);
                                    //saved inside the database
                                    Toast.makeText(SignUpActivity.this,
                                            "User created successfully!!!", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                    startActivity(intent);


                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(SignUpActivity.this,task.getException().getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        binding.tvAlrdyHvAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

//        if(auth.getCurrentUser()!=null)
//        {
//            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
//            startActivity(intent);
//        }



    }
}