package com.moodtrackerfinal.viewmodel;
/** ViewModel class **/
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.moodtrackerfinal.db.entity.MoodEntity;
import com.moodtrackerfinal.db.repository.DataRepository;

import java.util.List;
/** Provides data to the UI by acting as a communication center between the Repository and the UI **/
public class MoodListViewModel extends AndroidViewModel
{
    private DataRepository mRepository;
    private final LiveData<List<MoodEntity>> mAllMoods;
    private final LiveData<MoodEntity> mMood;
    // Constructor - Gets a reference to the repository and gets variables from it
    public MoodListViewModel(Application application)
    {
        super(application);
        mRepository = new DataRepository(application);
        mAllMoods = mRepository.getAllMoods();
        mMood = mRepository.getMood();
    }
    // Getter - Expose the LiveData getAllMoods query so the UI can observe it
    public LiveData<List<MoodEntity>> getAllMoods()
    {
        return mAllMoods;
    }
    // Getter - Expose the LiveData getMood query so the UI can observe it
    public LiveData<MoodEntity> getMood()
    {
        return mMood;
    }
    // Wrapper - Calls repository's update method
    public void update(MoodEntity mood)
    {
        mRepository.update(mood);
    }
}

