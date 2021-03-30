package com.example.imersprototype3;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateExperience extends AppCompatActivity {

    //region object variables
    public Button AddCharacter;
    public Button AddEvent;
    public Button AddBranch;
    public Button Save;
    public EditText ExperienceName;
    public EditText ShortDescription;
    public EditText DetailDescription;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_experience);

        // New instance of DB entry
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference newDBentry = database.getReference("message");

        //region EditText variables
        ExperienceName = (EditText) findViewById(R.id.editTextTextPersonName);
        ExperienceName.setHint("Name your experience");

        ShortDescription = (EditText) findViewById(R.id.editTextTextPersonName2);
        ShortDescription.setHint("Give it a short description");

        DetailDescription = (EditText) findViewById(R.id.editTextTextMultiLine2);
        DetailDescription.setHint("Describe it in more detail");
        //endregion

        AddCharacter = (Button) findViewById(R.id.addCharacter);
//        AddCharacter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(CreateExperience.this, AddCharacter.class);
//                startActivity(intent);
//            }
//        });

        AddEvent = (Button) findViewById(R.id.addEvent);
//        AddEvent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(CreateExperience.this, AddEvent.class);
//                startActivity(intent);
//            }
//        });

        AddBranch = (Button) findViewById(R.id.AddBranch);
//        AddBranch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(CreateExperience.this, AddBranch.class);
//                startActivity(intent);
//            }
//        });

        Save = (Button) findViewById(R.id.save);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateExperience.this, MainActivity.class);
                //newDBentry.setValue(ExperienceName.getText().toString());
                startActivity(intent);
            }
        });

    }

}