package com.moodtrackerfinal.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.moodtrackerfinal.db.entity.MoodEntity;

import java.util.List;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;
/****/
@Dao
public interface MoodDao
{
    @Query("SELECT * FROM moods")
    LiveData<List<MoodEntity>> getAllMoods();
    //
    @Insert
    //void insertAll(MoodEntity ... moods);
    void insertAll(List<MoodEntity> moods);
    //
    @Insert(onConflict = REPLACE)
    long insertMood(MoodEntity moods);
    //
    @Delete
    void delete(MoodEntity moods);
    //
    @Query("select * from moods where id = :moodId")
    LiveData<MoodEntity> loadMood(int moodId);
    @Update
    public void update(MoodEntity ... moods);
    //
    //@Query("SELECT * FROM mood WHERE date = ")
    //Mood loadLastWeekMoods(int date)
    //@Insert(onConflict = OnConflictStrategy.REPLACE)
    //void insertAll(List<Mood> moods);
}

