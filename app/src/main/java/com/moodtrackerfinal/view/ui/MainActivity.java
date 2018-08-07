package com.moodtrackerfinal.view.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.moodtrackerfinal.R;
import com.moodtrackerfinal.db.entity.MoodEntity;
import com.moodtrackerfinal.view.adapter.MoodAdapter;
import com.moodtrackerfinal.view.listener.OnSwipeTouchListener;
import com.moodtrackerfinal.viewmodel.MoodListViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private String currentMood = "normal";
    private MoodListViewModel viewModel;
    public List<MoodEntity> moodList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeTouchListener();
        sampleData();
    }

    public void sampleData()
    {
        MoodEntity mood = new MoodEntity (1,"normal","");
        moodList.add(mood);
        mood = new MoodEntity (2,"superhappy","J'ai mangé mes brocolis");
        moodList.add(mood);
        mood = new MoodEntity (3,"sad","");
        moodList.add(mood);
        mood = new MoodEntity (4,"happy","");
        moodList.add(mood);
        mood = new MoodEntity (5,"normal","");
        moodList.add(mood);
        mood = new MoodEntity (6,"disappointed","J'ai fait un bisou au chat il m'a pété au nez");
        moodList.add(mood);
        mood = new MoodEntity (7,"happy","J'adore les brocolis");
        moodList.add(mood);}
    public void showHistory(View view)
    {
        Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
        startActivity(intent);

    }
    public void addNote(View view)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(promptView);

        final EditText moodNote = (EditText) promptView.findViewById(R.id.edit_note);
        //moodNote.setText(moodList.get(0).getNote());
        alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                MoodEntity moodEdit = new MoodEntity(0,"" + currentMood, "" + moodNote.getText());
                //moodList.set(0, moodEdit);
                //adapter.notifyDataSetChanged();

                //String currentNote = moodList.get(0).getMoodNote();
                //String currentMoood = moodList.get(0).getMoodName();
                //Toast.makeText(MainActivity.this,  currentNote, Toast.LENGTH_SHORT).show();
                //Toast.makeText(MainActivity.this,  currentMoood, Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                dialog.cancel();
            }
        });
        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
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
