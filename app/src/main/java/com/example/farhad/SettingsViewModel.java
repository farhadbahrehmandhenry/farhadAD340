package com.example.farhad;

import android.content.Context;

import com.example.farhad.AppDatabase;
import com.example.farhad.AppDatabaseSingleton;
import com.example.farhad.Settings;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

//public class SettingsViewModel extends ViewModel {
//
//    public LiveData<Settings> loadSettingsById(Context context, Integer id) {
//        AppDatabase db = AppDatabaseSingleton.getDatabase(context);
//        return db.settingsDao().loadById(id);
//    }
//
//    public void updateSettings(Context context, Settings settings) {
//        AppDatabase db = AppDatabaseSingleton.getDatabase(context);
//        db.getTransactionExecutor().execute(() -> {
//            db.settingsDao().updateSettings(settings);
//        });
//    }
//
//    public void deleteSettings(Context context, Settings settings) {
//        AppDatabase db = AppDatabaseSingleton.getDatabase(context);
//        db.getTransactionExecutor().execute(() -> {
//            db.settingsDao().delete(settings);
//        });
//    }
//}

public class SettingsViewModel extends ViewModel {

    public LiveData<List<Settings>> loadSettingsByEmail(Context context, String[] email) {
        SettingsDatabase db = SettingsSingleton.getDatabase(context);
        return db.settingsDao().loadByEmail(email);
    }

    public LiveData<Settings> loadSettingsById(Context context, Integer id) {
        AppDatabase db = AppDatabaseSingleton.getDatabase(context);
        return db.settingsDao().loadById(id);
    }

    public void insertSettings(Context context, Settings... settings) {
        SettingsDatabase db = SettingsSingleton.getDatabase(context);
        db.getTransactionExecutor().execute(() -> {
            db.settingsDao().insert(settings);
        });
    }

    public void updateSettings(Context context, Settings settings) {
        SettingsDatabase db = SettingsSingleton.getDatabase(context);
        db.getTransactionExecutor().execute(() -> {
            db.settingsDao().updateSettings(settings);
        });
    }

}