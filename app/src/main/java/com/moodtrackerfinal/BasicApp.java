package com.moodtrackerfinal;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.facebook.stetho.Stetho;
import com.moodtrackerfinal.db.AppDatabase;

public class BasicApp extends Application
{
    private AppDatabase mDb;

    @Override
    public void onCreate() {
        super.onCreate();
        mDb = Room.databaseBuilder(this,
                AppDatabase.class, "mooddb").build();
        Stetho.initializeWithDefaults(this);
    }

    public static AppDatabase getDB(Context context) {
        return ((BasicApp) context.getApplicationContext()).mDb;
    }

    public static AppDatabase getDB(Fragment fragment) {
        return getDB(fragment.getContext());
    }

}
