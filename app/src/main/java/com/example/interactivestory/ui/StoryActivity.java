package com.example.interactivestory.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.interactivestory.R;
import com.example.interactivestory.model.Page;
import com.example.interactivestory.model.Story;

public class StoryActivity extends AppCompatActivity {
    public static final String TAG = StoryActivity.class.getSimpleName();
    private Story story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        Intent intent = getIntent();
        String name = intent.getStringExtra(getString(R.string.key_name));
        if(name == null || name.isEmpty()){
            name = "Friend"; //for the bug, rid of the crash without name
        }
        Log.d(TAG, name);
        story = new Story();
        loadpage(0);

    }//end onCreate

    private void loadpage(int pageNumber) {
        Page page = story.getPage(pageNumber);
    }
}
