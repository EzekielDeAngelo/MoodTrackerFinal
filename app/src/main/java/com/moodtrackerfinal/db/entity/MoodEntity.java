package com.moodtrackerfinal.db.entity;
/** Entity class **/
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.moodtrackerfinal.model.Mood;
/** Describes a mood Entity **/
@Entity(tableName = "mood_table")
public class MoodEntity implements Mood
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;
    @ColumnInfo(name = "name")
    private int mName;
    @ColumnInfo(name = "note")
    private String mNote;
    // Constructor
    public MoodEntity(int id, int name, String note)
    {
        this.mId = id;
        this.mName = name;
        this.mNote = note;
    }
    // Getter and setter methods
    @Override
    public int getId() { return mId; }
    public void setId(int id) { this.mId = id; }
    @Override
    public int getName() { return mName; }
    public void setName(int name) { this.mName = name; }
    @Override
    public String getNote() { return mNote; }
    public void setNote(String note) { this.mNote = note; }
}

