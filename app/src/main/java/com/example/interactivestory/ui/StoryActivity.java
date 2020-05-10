package com.example.interactivestory.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.interactivestory.R;
import com.example.interactivestory.model.Page;
import com.example.interactivestory.model.Story;

public class StoryActivity extends AppCompatActivity {
    public static final String TAG = StoryActivity.class.getSimpleName();
    private Story story;
    private ImageView storyImageView;
    private TextView storyTextView;
    private Button choice1Button;
    private Button choice2Button;
    private String name;

    // This is here it shows up the stories

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        storyImageView = findViewById(R.id.storyImageView);
        storyTextView = findViewById(R.id.storyTextView);
        choice1Button = findViewById(R.id.choice1Button);
        choice2Button = findViewById(R.id.choice2Button);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        if (name == null || name.isEmpty()) {
            name = "Friend"; //for the bug, rid of the crash without name
        }
        Log.d(TAG, name);
        story = new Story();
        loadpage(0);

    }//end onCreate

    private void loadpage(int pageNumber) {
        Page page = story.getPage(pageNumber);
        Drawable image = ContextCompat.getDrawable(this, page.getImageID());
        storyImageView.setImageDrawable(image);
        String pageText = getString(page.getTextID());
        //Add name if placeholder included, Wont add if not
        pageText = String.format(pageText, name);
        storyTextView.setText(pageText);

        choice1Button.setText(page.getChoice1().getTextID());
        choice2Button.setText(page.getChoice2().getTextID());
    }
}
