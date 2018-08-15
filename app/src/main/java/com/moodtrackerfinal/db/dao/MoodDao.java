package com.moodtrackerfinal.db.dao;
/****/
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
//import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

import com.moodtrackerfinal.db.entity.MoodEntity;

import java.util.List;
/****/
@Dao
public interface MoodDao
{
    @Query("SELECT * FROM mood_table")
    LiveData<List<MoodEntity>> getAllMoods();
    //
    @Query("select * from mood_table where mId = 8")
    LiveData<MoodEntity> getMood();
    //
    @Insert(onConflict = IGNORE)
    void insert(MoodEntity mood);
    //
    @Update
    void update(MoodEntity ... mood);
    //
    @Query("DELETE FROM mood_table")
    void deleteAll();
    //
    @Query("select * from mood_table where mId = :id")
    MoodEntity load(int id);
    //
    //@Delete
    //void delete(MoodEntity mood);
}

