package com.example.farhad;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Settings.class}, version = 3, exportSchema = false)
public abstract class SettingsDatabase extends RoomDatabase {
    public abstract SettingsDao settingsDao();
}