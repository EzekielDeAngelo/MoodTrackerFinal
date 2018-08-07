package com.moodtrackerfinal.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
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

@Database(entities = {MoodEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase
{
    public abstract MoodDao moodDao();
    /*private static AppDatabase sInstance;
    public static final String DATABASE_NAME = "mooddb";
    public abstract MoodDao moodDao();
    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public static AppDatabase getInstance(final Context context, final AppExecutors executors)
    {
        if (sInstance == null)
        {
            synchronized (AppDatabase.class)
            {
                if (sInstance == null)
                {
                    sInstance = buildDatabase(context.getApplicationContext(), executors);
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                    //sInstance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME).build();
                }
            }
        }
        return sInstance;
    }

    private static AppDatabase buildDatabase ( final Context appContext, final AppExecutors executors)
    {
        return Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME).addCallback(new Callback()
        {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db)
            {
                super.onCreate(db);
                executors.diskIO().execute(() ->
                {
                    //Add a delay to simulate a long-running operation
                    addDelay();
                    // Generate the data for pre-population
                    AppDatabase database = AppDatabase.getInstance(appContext, executors);
                    List<MoodEntity> products = DataGenerator.generateProducts();
                    insertData(database, products);
                    // notify that the database was created and it's ready to be used
                    database.setDatabaseCreated();
                });
            }
        }).build();
    }

    /**
     * Check whether the database already exists and expose it via {@link #getDatabaseCreated()}
     */
   /* private void updateDatabaseCreated ( final Context context){
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated () {
        mIsDatabaseCreated.postValue(true);
    }

    private static void insertData (final AppDatabase database, final List<MoodEntity> moods)
    {
        database.runInTransaction(() ->
        {
            database.moodDao().insertAll(moods);
        });
    }

    private static void addDelay () {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ignored) {
        }
    }

    public LiveData<Boolean> getDatabaseCreated () {
        return mIsDatabaseCreated;
    }*/
}

