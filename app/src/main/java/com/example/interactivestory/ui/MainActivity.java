package com.example.interactivestory.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.interactivestory.R;

public class MainActivity extends AppCompatActivity {
    private EditText nameField;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameField = findViewById(R.id.nameEditText);
        startButton = findViewById(R.id.startButton);

    }

    //method for start button
    public void buttonPressed(View view) {
        String name = nameField.getText().toString();
        startStory(name);
        //  Toast.makeText(this, name, Toast.LENGTH_LONG).show();
    }//end buttonPressed

    //when user has pressed the button it will switch to another activity..
    private void startStory(String name) {
        Intent intent = new Intent(this, StoryActivity.class);
        Resources resources = getResources();
        String key = resources.getString(R.string.key_name);
        intent.putExtra(key,name);
        startActivity(intent);
    }

}
