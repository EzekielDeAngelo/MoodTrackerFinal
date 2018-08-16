package com.moodtrackerfinal.db.dao;
/** Data access object class **/
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

import com.moodtrackerfinal.db.entity.MoodEntity;

import java.util.List;
/** Specify SQL queries and associate them with method calls **/
@Dao
public interface MoodDao
{
    // SQL query for viewModel to get all moods from mood table
    @Query("SELECT * FROM mood_table")
    LiveData<List<MoodEntity>> getAllMoods();
    // SQL query for viewModel to get the mood of the day from mood table
    @Query("select * from mood_table where id = 8")
    LiveData<MoodEntity> getMood();
    // SQL query to insert a mood into mood table
    @Insert(onConflict = IGNORE)
    void insert(MoodEntity mood);
    // SQL query to update a mood from mood table
    @Update
    void update(MoodEntity ... mood);
    // SQL query to load a mood from mood table
    @Query("select * from mood_table where id = :id")
    MoodEntity load(int id);
}

