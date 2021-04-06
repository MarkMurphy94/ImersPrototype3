package com.example.imersprototype3;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class CreateExperience extends AppCompatActivity {

    public Button AddCharacter, AddEvent, AddBranch, Save;
    public EditText ExperienceName, ShortDescription, DetailDescription;
    String filename = "";
    String filepath = "";
    String filecontent = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_experience);

        // New instance of DB entry
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference newDBentry = database.getReference("message");

        //region EditText variables
        ExperienceName = findViewById(R.id.ExperienceName);
        ExperienceName.setHint("Name your experience");

        ShortDescription = findViewById(R.id.ShortDescription);
        ShortDescription.setHint("Give it a short description");

        DetailDescription = findViewById(R.id.DetailDescription);
        DetailDescription.setHint("Describe it in more detail");
        //endregion

        Save = findViewById(R.id.save);

        filename = "";
        filepath = "My Experiences";

        if(!ExternalStorageAvailableForRW()){
            Save.setEnabled(false);
        }

        AddCharacter = findViewById(R.id.addCharacter);
        AddCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateExperience.this, AddCharacter.class);
                startActivity(intent);
            }
        });

//        AddEvent = (Button) findViewById(R.id.addEvent);
//        AddEvent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(CreateExperience.this, AddEvent.class);
//                startActivity(intent);
//            }
//        });
//
//        AddBranch = (Button) findViewById(R.id.AddBranch);
//        AddBranch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(CreateExperience.this, AddBranch.class);
//                startActivity(intent);
//            }
//        });

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateExperience.this, MainActivity.class);
                filename = ExperienceName.getText().toString().trim();
                filecontent = ShortDescription.getText().toString().trim();
                String Detail = DetailDescription.getText().toString().trim();

                if(!filecontent.equals("")){
                    //File newExperience = new File(getExternalFilesDir(filepath), filename + ".txt");
                    //FileOutputStream fos = null;
                    writeToFile(filecontent);

                    Toast.makeText(CreateExperience.this, "Experience saved", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(CreateExperience.this, "Enter a name for the experience", Toast.LENGTH_SHORT).show();
                }

                startActivity(intent);
            }
        });

    }

    private boolean ExternalStorageAvailableForRW(){
        String state = Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED)){
            return true;
        }
        return false;
    }

    private void writeToFile(String content) {
        try {
            File file = new File(getExternalFilesDir(filepath), filename + ".txt");

            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file);
            writer.append(content);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}