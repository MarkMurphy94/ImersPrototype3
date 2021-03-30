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
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccount extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    private EditText add_name, add_email, add_username, add_password;
    private Button create, back;
    private ProgressBar progress_bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        mAuth = FirebaseAuth.getInstance();

        add_name = (EditText) findViewById(R.id.add_name);
        add_email = (EditText) findViewById(R.id.add_email);
        add_username = (EditText) findViewById(R.id.add_username);
        add_password = (EditText) findViewById(R.id.add_password);

        create = (Button) findViewById(R.id.create);
        back = (Button) findViewById(R.id.back);
        create.setOnClickListener(this);

        progress_bar = (ProgressBar) findViewById(R.id.progressBar2);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.create:
                createUser();
                break;
            case R.id.back:
                startActivity(new Intent(this, Login.class));
                break;
        }
    }

    private void createUser() {
        final String name = add_name.getText().toString().trim();
        final String email = add_email.getText().toString().trim();
        final String username = add_username.getText().toString().trim();
        String password = add_password.getText().toString().trim();

        if(name.isEmpty()){
            add_name.setError("Name is required");
            add_name.requestFocus();
            return;
        }

        if(email.isEmpty()){
            add_email.setError("Email address is required");
            add_email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            add_email.setError("Please enter a valid email address");
            add_email.requestFocus();
        }

        if(username.isEmpty()){
            add_username.setError("Please create a username");
            add_username.requestFocus();
            return;
        }

        if(password.isEmpty()){
            add_password.setError("Please create a password");
            add_password.requestFocus();
            return;
        }

        if(password.length() < 6){
            add_password.setError("Password must be 6 characters minimum");
            add_password.requestFocus();
        }

        progress_bar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(name, email, username);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(CreateAccount.this, "Account created!", Toast.LENGTH_LONG).show();
                                        progress_bar.setVisibility(View.GONE);
                                        //return to login screen

                                    }else{
                                        Toast.makeText(CreateAccount.this, "Failed to create account", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(CreateAccount.this, "Account created!", Toast.LENGTH_LONG).show();
                            progress_bar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}