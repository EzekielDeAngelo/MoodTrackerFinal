package com.moodtrackerfinal.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import com.moodtrackerfinal.BasicApp;
import com.moodtrackerfinal.db.entity.MoodEntity;

import java.util.List;

public class MoodListViewModel extends AndroidViewModel {

   private final LiveData<List<MoodEntity>> moodList;
   public MoodListViewModel(Application application)
   {
       super(application);
       moodList = BasicApp.getDB(application).moodDao().getAllMoods();
   }


    /**
     * Expose the LiveData Products query so the UI can observe it.
     */
    public LiveData<List<MoodEntity>> getMoods() {
        return moodList;
    }
}

