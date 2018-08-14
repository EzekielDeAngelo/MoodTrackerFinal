package com.moodtrackerfinal.db;
/****/
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.moodtrackerfinal.AppExecutors;
import com.moodtrackerfinal.db.dao.MoodDao;
import com.moodtrackerfinal.db.entity.MoodEntity;

import java.util.List;

/****/
@Database(entities = {MoodEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase
{
    public abstract MoodDao moodDao();
    private static AppDatabase INSTANCE;
    public static final String DATABASE_NAME = "mood_database";
    //
    public static AppDatabase getDatabase(final Context context, final AppExecutors executors)
    {
        if (INSTANCE == null)
        {
            synchronized (AppDatabase.class)
            {
                if (INSTANCE == null)
                {
                    INSTANCE = buildDatabase(context.getApplicationContext(), executors);
                }
            }
        }
        return INSTANCE;
    }
    //
    private static AppDatabase buildDatabase(final Context appContext, final AppExecutors executors)
    {
        return Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);

                        executors.diskIO().execute(() ->
                        {
                            new DataGenerator(INSTANCE).execute();
                        });
                    }
                }).build();
    }
}

