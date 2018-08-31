package com.moodtrackerfinal.view.ui;
/** Main activity class **/
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.moodtrackerfinal.R;
import com.moodtrackerfinal.MoodJobService;
import com.moodtrackerfinal.db.entity.MoodEntity;
import com.moodtrackerfinal.view.listener.OnSwipeTouchListener;
import com.moodtrackerfinal.viewmodel.MoodListViewModel;
/** Handles UI for main activity **/
public class MainActivity extends AppCompatActivity
{
    public MoodListViewModel mViewModel;
    private int currentMoodName = 3;
    private String currentMoodNote = "";
    private static final String TAG = "MainActivity";
    // Override onCreate method - Calls method to handle swipe gestures and observe viewModel
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeTouchListener();
        scheduleJob();
        mViewModel = ViewModelProviders.of(this).get(MoodListViewModel.class);
        mViewModel.getMood().observe(this, new Observer<MoodEntity>()
        {
            @Override
            final public void onChanged(@Nullable MoodEntity mood)
            {
                if (mood != null)
                {
                    currentMoodNote = mood.getNote();
                    currentMoodName = mood.getName();
                    setBackground();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    // Set background method - Modify main activity background color and mood smiley
    public void setBackground()
    {
        ImageView moodSmiley = findViewById(R.id.mood_smiley);
        ImageView moodColor = findViewById(R.id.mood_color);
        if (moodColor != null) {
            switch (currentMoodName) {
                case 1:
                    moodSmiley.setImageResource(R.drawable.smileysad);
                    moodColor.setBackgroundResource(R.color.sad);
                    break;
                case 2:
                    moodSmiley.setImageResource(R.drawable.smileydisappointed);
                    moodColor.setBackgroundResource(R.color.disappointed);
                    break;
                case 3:
                    moodSmiley.setImageResource(R.drawable.smileynormal);
                    moodColor.setBackgroundResource(R.color.normal);
                    break;
                case 4:
                    moodSmiley.setImageResource(R.drawable.smileyhappy);
                    moodColor.setBackgroundResource(R.color.happy);
                    break;
                case 5:
                    moodSmiley.setImageResource(R.drawable.smileysuperhappy);
                    moodColor.setBackgroundResource(R.color.superhappy);
                    break;
            }
        }
    }
    // Schedule job method - Builds a jobInfo object with given criteria
    public void scheduleJob()
    {
        ComponentName componentName = new ComponentName(this, MoodJobService.class);
        JobInfo info = new JobInfo.Builder(123,componentName).setRequiresCharging(true).setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED).setPersisted(true).setPeriodic(24 * 60 * 60 * 1000).build();
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(info);
        if ( resultCode == JobScheduler.RESULT_SUCCESS)
        {
            Log.d(TAG, "Job scheduled");
        }
        else
        {
            Log.d(TAG, "Job scheduling failed");
        }
    }
    // onClick method bound with show_history button - Starts a new history activity
    public void showHistory(View view)
    {
        Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
        startActivity(intent);
    }
    // onClick method bound with add_note button - Inflates a layout for text edition
    public void addNote(View view)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(promptView);

        final EditText editNote = (EditText) promptView.findViewById(R.id.edit_note);
        editNote.setText(currentMoodNote);
        alertDialogBuilder.setCancelable(false)
        .setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                currentMoodNote = editNote.getText().toString();
                MoodEntity currentMood = new MoodEntity(8, currentMoodName, currentMoodNote);
                mViewModel.update(currentMood);
            }
        })
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                dialog.cancel();
            }
        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
    // Swipe touch listener method
    @SuppressLint("ClickableViewAccessibility")
    private void swipeTouchListener()
    {
        ImageView moodColor = findViewById(R.id.mood_color);
        moodColor.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this)
        {
            public void onSwipeBottom()
            {
                if (currentMoodName == 1)
                {
                    currentMoodName = 5;
                }
                else
                {
                    currentMoodName--;
                }
                setMoodName(currentMoodName, currentMoodNote);
            }
            public void onSwipeTop()
            {
                if (currentMoodName == 5)
                {
                    currentMoodName = 1;
                }
                else
                {
                    currentMoodName++;
                }
                setMoodName(currentMoodName, currentMoodNote);
            }
        });
    }
    // Set mood name - Update mood of the day with current parameters
    public void setMoodName(int moodName, String moodNote)
    {
        MoodEntity mood = new MoodEntity(8, moodName, moodNote);
        mViewModel.update(mood);
    }
}
