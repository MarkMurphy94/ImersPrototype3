package com.example.imersprototype3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button login, create_account_button;
    private ProgressBar progressBar;
    private EditText email_entry, password_entry;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        create_account_button = findViewById(R.id.create_account_button);
        create_account_button.setOnClickListener(this);
        login = findViewById(R.id.login);
        login.setOnClickListener(this);

        email_entry = findViewById(R.id.email_entry);
        password_entry = findViewById(R.id.password_entry);
        progressBar = findViewById(R.id.progressBar);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.create_account_button:
                startActivity(new Intent(this, CreateAccount.class));
                break;
            case R.id.login:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        String email = email_entry.getText().toString().trim();
        String password = password_entry.getText().toString().trim();

        if(email.isEmpty()){
            email_entry.setError("Please enter an email address");
            email_entry.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            email_entry.setError("Please enter a valid email address");
            email_entry.requestFocus();
            return;
        }

        if(password.isEmpty()){
            password_entry.setError("Please enter your password");
            password_entry.requestFocus();
            return;
        }

        if(password.length() < 6){
            password_entry.setError("Please enter a valid password");
            password_entry.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(Login.this, MainActivity.class));
                }else{
                    Toast.makeText(Login.this, "Failed to login. Please check your credentials", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
