package com.moodtrackerfinal.view.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.moodtrackerfinal.R;
import com.moodtrackerfinal.view.listener.OnSwipeTouchListener;

public class MainActivity extends AppCompatActivity
{
    private String currentMood = "normal";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeTouchListener();
    }


    public void showHistory(View view)
    {
        Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
        startActivity(intent);
    }
    public void addNote(View view)
    {
        //addDailyNote();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void swipeTouchListener()
    {
        ImageView moodColor = findViewById(R.id.mood_color);
        moodColor.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this)
        {
            public void onSwipeBottom()
            {
                ImageView moodSmiley = findViewById(R.id.mood_smiley);
                String moodName = String.valueOf(moodSmiley.getTag());
                switch (moodName)
                {
                    case "smileysad":
                        moodSmiley.setImageResource(R.drawable.smileysuperhappy);
                        moodSmiley.setTag("smileysuperhappy");
                        moodColor.setBackgroundResource(R.color.superhappy);
                        currentMood = "superhappy";
                        break;
                    case "smileydisappointed":
                        moodSmiley.setImageResource(R.drawable.smileysad);
                        moodSmiley.setTag("smileysad");
                        moodColor.setBackgroundResource(R.color.sad);
                        currentMood = "sad";
                        break;
                    case "smileynormal":
                        moodSmiley.setImageResource(R.drawable.smileydisappointed);
                        moodSmiley.setTag("smileydisappointed");
                        moodColor.setBackgroundResource(R.color.disappointed);
                        currentMood = "disappointed";
                        break;
                    case "smileyhappy":
                        moodSmiley.setImageResource(R.drawable.smileynormal);
                        moodSmiley.setTag("smileynormal");
                        moodColor.setBackgroundResource(R.color.normal);
                        currentMood = "normal";
                        break;
                    case "smileysuperhappy":
                        moodSmiley.setImageResource(R.drawable.smileyhappy);
                        moodSmiley.setTag("smileyhappy");
                        moodColor.setBackgroundResource(R.color.happy);
                        currentMood = "happy";
                        break;
                }
            }
            public void onSwipeTop()
            {
                ImageView moodSmiley = findViewById(R.id.mood_smiley);
                String moodName = String.valueOf(moodSmiley.getTag());
                switch (moodName)
                {
                    case "smileysad":
                        moodSmiley.setImageResource(R.drawable.smileydisappointed);
                        moodSmiley.setTag("smileydisappointed");
                        moodColor.setBackgroundResource(R.color.disappointed);
                        currentMood = "disappointed";
                        break;
                    case "smileydisappointed":
                        moodSmiley.setImageResource(R.drawable.smileynormal);
                        moodSmiley.setTag("smileynormal");
                        moodColor.setBackgroundResource(R.color.normal);
                        currentMood = "normal";
                        break;
                    case "smileynormal":
                        moodSmiley.setImageResource(R.drawable.smileyhappy);
                        moodSmiley.setTag("smileyhappy");
                        moodColor.setBackgroundResource(R.color.happy);
                        currentMood = "happy";
                        break;
                    case "smileyhappy":
                        moodSmiley.setImageResource(R.drawable.smileysuperhappy);
                        moodSmiley.setTag("smileysuperhappy");
                        moodColor.setBackgroundResource(R.color.superhappy);
                        currentMood = "superhappy";
                        break;
                    case "smileysuperhappy":
                        moodSmiley.setImageResource(R.drawable.smileysad);
                        moodSmiley.setTag("smileysad");
                        moodColor.setBackgroundResource(R.color.sad);
                        currentMood = "sad";
                        break;
                }
            }
        });
    }
}
