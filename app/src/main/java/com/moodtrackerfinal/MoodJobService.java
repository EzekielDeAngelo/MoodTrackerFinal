package com.moodtrackerfinal;
/** Job service class **/
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

import com.moodtrackerfinal.db.entity.MoodEntity;
import com.moodtrackerfinal.db.repository.DataRepository;
import com.moodtrackerfinal.view.ui.MainActivity;

/** Enables the system to perform work, regardless of whether the app is active **/
public class MoodJobService extends JobService
{
    private static final String TAG = "MoodJobService";
    private boolean jobCancelled = false;
    // Override onStartJob method - Gets called by the system when it is time for the job to execute
    @Override
    public boolean onStartJob(JobParameters params )
    {
        Log.d(TAG,"Job started");
        doBackgroundWork(params);
        return true;
    }
    // Do background work method - Runs the scheduled task
    private void doBackgroundWork(JobParameters params)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                DataRepository repository = new DataRepository(getApplication());
                for (int i = 6 ; i > 0 ; i--)
                {
                    MoodEntity mood = repository.load(i);
                    mood.setId(i + 1);
                    Log.d(TAG,  mood.getId() + " " + mood.getName() + " " + mood.getNote());
                    repository.update(mood);
                }
                MoodEntity mood = repository.load(8);
                mood.setId(1);
                repository.update(mood);
                mood = new MoodEntity(8, 4, "");
                mood.setId(8);
                repository.update(mood);
                Log.d(TAG, "Job finished");
                jobFinished(params, false);
            }
        }).start();
    }
    // Override onStopJob method - Gets called by the system if the job is cancelled before being finished
    @Override
    public boolean onStopJob( JobParameters params )
    {
        Log.d(TAG, "Job cancelled before completion");
        jobCancelled = true;
        return true;
    }
}
