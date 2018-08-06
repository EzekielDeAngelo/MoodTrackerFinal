package com.moodtrackerfinal.db.entity;

/****/
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.moodtrackerfinal.model.Mood;
/****/
@Entity(tableName = "moods")
public class MoodEntity implements Mood
{
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "moodName")
    private String name;
    @ColumnInfo(name = "moodNote")
    private String note;
    /* @ColumnInfo(name = "moodDate")
     private String date;*/
    //
    @Override
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    @Override
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    @Override
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    //
    public MoodEntity()
    {
    }
    public MoodEntity(int id, String name, String note)
    {
        this.id = id;
        this.name = name;
        this.note = note;
    }
    //    @Ignore
    public MoodEntity(Mood mood)
    {
        this.id = mood.getId();
        this.name = mood.getName();
        this.note = mood.getNote();
    }
}