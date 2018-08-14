package com.moodtrackerfinal.db.repository;
/****/
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.moodtrackerfinal.AppExecutors;
import com.moodtrackerfinal.db.AppDatabase;
import com.moodtrackerfinal.db.dao.MoodDao;
import com.moodtrackerfinal.db.entity.MoodEntity;

import java.util.List;
/****/
public class DataRepository
{

    private LiveData<MoodEntity> mMood;
    private LiveData<List<MoodEntity>> mAllMoods;
    private MoodDao mMoodDao;
    //private static DataRepository sInstance;
    private AppExecutors executors;
    public DataRepository(Application application)
    {
        executors = new AppExecutors();
        AppDatabase db = AppDatabase.getDatabase(application, executors);
        mMoodDao = db.moodDao();
        mAllMoods = mMoodDao.getAllMoods();
        mMood = mMoodDao.getMood();
    }
    //
    public LiveData<List<MoodEntity>> getAllMoods()
    {
        return mAllMoods;
    }
    //
    public LiveData<MoodEntity> getMood()
    {
        return mMood;
    }
    //
    public void update(MoodEntity mood)
    {
        new updateAsyncTask(mMoodDao).execute(mood);
    }
    //
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
    //
    /*public void insert(MoodEntity mood)
    {
        new insertAsyncTask(mMoodDao).execute(mood);

    }
    //
    private static class insertAsyncTask extends AsyncTask<MoodEntity, Void, Void>
    {
        private MoodDao mAsyncTaskDao;
        insertAsyncTask(MoodDao dao)
        {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final MoodEntity... params)
        {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }*/
}
