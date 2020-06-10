package com.example.farhad;

import android.app.Application;
import android.content.Context;
import com.example.farhad.Settings;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class SettingsViewModel extends ViewModel {

    public LiveData<List<Settings>> loadSettingsByEmail(Context context, String[] email) {
        SettingsDatabase db = SettingsSingleton.getDatabase(context);
        return db.settingsDao().loadByEmail(email);
    }

    public void insertSettings(Context context, Settings... settings) {
        SettingsDatabase db = SettingsSingleton.getDatabase(context);
        db.getTransactionExecutor().execute(() -> {
            db.settingsDao().insert(settings);
        });
    }

}