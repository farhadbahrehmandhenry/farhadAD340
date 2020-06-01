package com.example.farhad;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class SettingViewModel extends AndroidViewModel {
    private SettingsRepo repository;
    private LiveData<List<Settings>> allSettings;

    public SettingViewModel(@NonNull Application application) {
        super(application);

        repository = new SettingsRepo((application));

        allSettings = repository.getAllSettings();
    }

    public LiveData<List<Settings>> getSettings() { return allSettings; }

    public void insert(Settings settings) {
        repository.insert(settings);
    }

    public void update(Settings settings) {
        repository.update(settings);
    }

    public LiveData<List<Settings>> getAllSettings() {
        return allSettings;
    }
}
