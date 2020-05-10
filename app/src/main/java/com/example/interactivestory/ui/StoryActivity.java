package com.example.interactivestory.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.interactivestory.R;
import com.example.interactivestory.model.Page;
import com.example.interactivestory.model.Story;

import java.util.Stack;

public class StoryActivity extends AppCompatActivity {
    public static final String TAG = StoryActivity.class.getSimpleName();
    private Story story;
    private ImageView storyImageView;
    private TextView storyTextView;
    private Button choice1Button;
    private Button choice2Button;
    private String name;
    private Stack<Integer> pageStack = new Stack<>();

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
        pageStack.push(pageNumber); //this is going to build up a stack of the pages as we go through them
        final Page page = story.getPage(pageNumber);
        Drawable image = ContextCompat.getDrawable(this, page.getImageID());
        storyImageView.setImageDrawable(image);
        String pageText = getString(page.getTextID());
        //Add name if placeholder included, Wont add if not
        pageText = String.format(pageText, name);
        storyTextView.setText(pageText);
        if (page.isFinalPage()) {
            choice1Button.setVisibility(View.INVISIBLE);
            choice2Button.setText(R.string.play_again_button_text);
            choice2Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //   finish(); //After end it will go to start
                    loadpage(0);
                }
            });
        } else {
            loadButtons(page);
        }//end else if final page...
    }//end loadpage

    private void loadButtons(final Page page) {
        choice1Button.setVisibility(View.VISIBLE);
        choice1Button.setText(page.getChoice1().getTextID());
        choice1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextPage = page.getChoice1().getNextPage();
                loadpage(nextPage);
            }
        });// choice1ButtonMethod
        choice2Button.setVisibility(View.VISIBLE);
        choice2Button.setText(page.getChoice2().getTextID());
        choice2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextPage = page.getChoice2().getNextPage();
                loadpage(nextPage);
            }
        });//end choice2buttonMethod
    }//end loadButtons

    //pop the stack eac htime the user taps on the back button

    @Override
    public void onBackPressed() {
        //it will pop or exit the current activity and go bac kto main activity, no matter which page of the story we are on
        pageStack.pop();
        if(pageStack.isEmpty()) {
            super.onBackPressed();
        }else {
            loadpage(pageStack.pop());
        }
    }//end onBackPressed
}// end class
