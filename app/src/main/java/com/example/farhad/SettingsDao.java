package com.example.farhad;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.farhad.Settings;

import java.util.List;

@Dao
public interface SettingsDao {

    @Query("SELECT * FROM settings")
    LiveData<List<Settings>> getAll();

    @Query("SELECT * FROM settings WHERE email LIKE :email LIMIT 1")
    LiveData<Settings> findByEmail(String email);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Settings userSettings);
}