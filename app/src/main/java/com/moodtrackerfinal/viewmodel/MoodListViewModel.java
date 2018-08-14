package com.moodtrackerfinal.viewmodel;
/****/
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.moodtrackerfinal.db.entity.MoodEntity;
import com.moodtrackerfinal.db.repository.DataRepository;

import java.util.List;
/****/
public class MoodListViewModel extends AndroidViewModel
{
    private DataRepository mRepository;
    private final LiveData<List<MoodEntity>> mAllMoods;
    private final LiveData<MoodEntity> mMood;

    public MoodListViewModel(Application application)
    {
        super(application);
        mRepository = new DataRepository(application);
        mAllMoods = mRepository.getAllMoods();
        mMood = mRepository.getMood();
    }
    //Expose the LiveData Products query so the UI can observe it.
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
        mRepository.update(mood);
    }
    /*public void insert(MoodEntity mood)
    {
        mRepository.insert(mood);
    }*/
}

