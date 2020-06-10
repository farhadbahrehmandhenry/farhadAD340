package com.example.farhad;

import android.app.Application;
import android.content.Context;
import com.example.farhad.Settings;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class SettingsViewModel extends AndroidViewModel {

    private SettingsRepository repo;

    public SettingsViewModel (Application application) {
        super(application);
        repo = new SettingsRepository(application);
    }

    public void insert(Settings userSettings) { repo.insert(userSettings); }

    public LiveData<Settings> findSettingsByEmail(String email) { return repo.findSettingsByEmail(email); }

}