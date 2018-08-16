package com.moodtrackerfinal.db.repository;
/** Repository class **/
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.moodtrackerfinal.AppExecutors;
import com.moodtrackerfinal.db.AppDatabase;
import com.moodtrackerfinal.db.dao.MoodDao;
import com.moodtrackerfinal.db.entity.MoodEntity;

import java.util.List;
/** Abstract access to data source to handle data operations **/
public class DataRepository
{
    private MoodDao mMoodDao;
    private LiveData<MoodEntity> mMood;
    private LiveData<List<MoodEntity>> mAllMoods;
    // Constructor - Gets a handle to the database and initializes the members variables
    public DataRepository(Application application)
    {
        AppExecutors executors = new AppExecutors();
        AppDatabase db = AppDatabase.getDatabase(application, executors);
        mMoodDao = db.moodDao();
        mAllMoods = mMoodDao.getAllMoods();
        mMood = mMoodDao.getMood();
    }
    // Wrapper - Observed LiveData will notify the observer when the data has changed
    public LiveData<List<MoodEntity>> getAllMoods()
    {
        return mAllMoods;
    }
    public LiveData<MoodEntity> getMood()
    {
        return mMood;
    }
    // Wrapper - Load
    public MoodEntity load(int i)
    {
        return mMoodDao.load(i);
    }
    // Wrapper - Update
    public void update(MoodEntity mood)
    {
        new updateAsyncTask(mMoodDao).execute(mood);
    }
    private static class updateAsyncTask extends AsyncTask<MoodEntity,Void,Void>
    {
        private MoodDao mAsyncTaskDao;
        updateAsyncTask(MoodDao dao)
        {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final MoodEntity... params)
        {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }
}
