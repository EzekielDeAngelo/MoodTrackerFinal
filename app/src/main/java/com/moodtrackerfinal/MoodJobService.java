package com.moodtrackerfinal;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.moodtrackerfinal.db.entity.MoodEntity;
import com.moodtrackerfinal.db.repository.DataRepository;
import com.moodtrackerfinal.view.ui.MainActivity;
import com.moodtrackerfinal.viewmodel.MoodListViewModel;

import java.util.List;

public class MoodJobService extends JobService
{
    /*private static final String TAG = MoodJobService.class.getSimpleName();

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        PersistableBundle bundle = jobParameters.getExtras();
        Log.v(TAG, bundle.getString("Title") + " started");
        jobFinished(jobParameters, false);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.v(TAG, "Job completed");
        return true;
    }*/





    private Handler mJobHandler = new Handler( new Handler.Callback()
    {
        @Override
        public boolean handleMessage( Message msg )
        {
            Toast.makeText( getApplicationContext(), "JobService task running", Toast.LENGTH_SHORT ).show();
            jobFinished( (JobParameters) msg.obj, false );
            return true;
        }
    });


    private static final String TAG = "MoodJobService";
    private boolean jobCancelled = false;

    @Override
    public boolean onStartJob(JobParameters params )
    {
    //    mJobHandler.sendMessage( Message.obtain( mJobHandler, 1, params ) );
        Log.d(TAG,"Job started");
        doBackgroundWork(params);
        return true;
    }
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
                //Toast.makeText(getApplicationContext(), mood.getId() + " " + mood.getName() + " " + mood.getNote(), Toast.LENGTH_SHORT).show();
                repository.update(mood);
            }
            MoodEntity mood = repository.load(8);
            mood.setId(1);
            repository.update(mood);
            Log.d(TAG, "Job finished");

            jobFinished(params, false);
        }
    }).start();
}
    @Override
    public boolean onStopJob( JobParameters params )
    {
        Log.d(TAG, "Job cancelled before completion");
        jobCancelled = true;
        return true;
    }
}
