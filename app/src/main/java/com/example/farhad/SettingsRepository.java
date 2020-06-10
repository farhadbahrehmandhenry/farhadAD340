package com.example.farhad;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;

public class SettingsRepository {
    private SettingsDao userSettingsDao;
    private LiveData<List<Settings>> allUserSettings;

    SettingsRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        userSettingsDao = db.userSettingsDao();
        allUserSettings = userSettingsDao.getAll();
    }

    void insert(Settings userSettings) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            userSettingsDao.insert(userSettings);
        });
    }

    LiveData<Settings> findSettingsByEmail(String email) {
        return userSettingsDao.findByEmail(email);
    }
}