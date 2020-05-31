package com.example.farhad;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SettingsDao {
    @Update
    void update(Settings settings);
    @Insert
    void insert(Settings setting);

    @Query("DELETE FROM setting_table")
    void deleteAllSettings();

    @Query("SELECT * FROM setting_table")
    LiveData<List<Settings>> getAllSettings();
}
