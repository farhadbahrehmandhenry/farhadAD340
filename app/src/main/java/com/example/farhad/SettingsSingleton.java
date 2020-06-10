package com.example.farhad;

import android.content.Context;

import androidx.room.Room;

public class SettingsSingleton {

    private static SettingsDatabase db;

    public static SettingsDatabase getDatabase(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context,
                    SettingsDatabase.class, "settings_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return db;
    }

}