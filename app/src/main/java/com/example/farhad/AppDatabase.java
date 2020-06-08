package com.example.farhad;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.farhad.SettingsDao;
import com.example.farhad.Settings;

@Database(entities = {Settings.class}, version = 4)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SettingsDao settingsDao();
}

