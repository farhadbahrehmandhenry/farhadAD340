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
//
@Dao
public interface SettingsDao {

    @Query("SELECT * FROM settings WHERE id = (:id) LIMIT 1")
    LiveData<Settings> loadById(Integer id);

    @Update
    void updateSettings(Settings settings);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSettings(Settings... settings);

    @Delete
    void delete(Settings settings);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Settings... settings);

    @Query("SELECT * FROM settings WHERE email = :email")
    LiveData<List<Settings>> loadByEmail(String[] email);
}