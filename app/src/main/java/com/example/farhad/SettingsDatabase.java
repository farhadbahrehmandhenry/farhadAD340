package com.example.farhad;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Settings.class}, version = 3)
public abstract class SettingsDatabase extends RoomDatabase {
    private static SettingsDatabase instance;
    public abstract SettingsDao settingsDao();

    public static synchronized SettingsDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    SettingsDatabase.class, "settings_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private SettingsDao settingsDao;
        private PopulateDbAsyncTask(SettingsDatabase db) {
            settingsDao = db.settingsDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            settingsDao.insert(new Settings(2, 2, 0, 0, 18));
            return null;
        }
    }
}
