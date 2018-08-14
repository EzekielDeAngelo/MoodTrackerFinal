package com.moodtrackerfinal.db;
/****/
import android.os.AsyncTask;

import com.moodtrackerfinal.db.dao.MoodDao;
import com.moodtrackerfinal.db.entity.MoodEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**Generates data to pre-populate the database**/
public class DataGenerator extends AsyncTask<Void,Void,Void>
{
    private final MoodDao mDao;
    DataGenerator(AppDatabase db)
    {
        mDao = db.moodDao();
    }
    //
    @Override
    protected Void doInBackground(final Void... params)
    {
        //mDao.deleteAll();
        MoodEntity mood = new MoodEntity(8,3,"");
        mDao.insert(mood);
        mood = new MoodEntity(1,3,"I had a nice day");
        mDao.insert(mood);
        mood = new MoodEntity(2,2,"I had a not so nice day");
        mDao.insert(mood);
        mood = new MoodEntity(3,1,"");
        mDao.insert(mood);
        mood = new MoodEntity(4,4,"I had a wonderful day");
        mDao.insert(mood);
        mood = new MoodEntity(5,5,"I had an amazing day");
        mDao.insert(mood);
        mood = new MoodEntity(6,3,"I had an average day");
        mDao.insert(mood);
        mood = new MoodEntity(7,4,"MAIS OUI");
        mDao.insert(mood);
        return null;
    }
}
