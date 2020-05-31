package com.example.farhad;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SettingsRepo {
    private SettingsDao settingsDao;
    private LiveData<List<Settings>> allSettings;
    public SettingsRepo(Application application) {
        SettingsDatabase database = SettingsDatabase.getInstance(application);
        settingsDao = database.settingsDao();
        allSettings = settingsDao.getAllSettings();
    }

    public void insert (Settings settings) {
        new insertSettingsAsyncTask(settingsDao).execute((settings));
    }

    public void update (Settings settings) {
        new insertSettingsAsyncTask(settingsDao).execute((settings));
    }

    public LiveData<List<Settings>> getAllSettings(){
        return allSettings;
    }

    private static class insertSettingsAsyncTask extends AsyncTask<Settings, Void, Void> {
        private SettingsDao settingsDao;
        private insertSettingsAsyncTask(SettingsDao settingsDao) {
            this.settingsDao = settingsDao;
        }

        @Override
        protected Void doInBackground(Settings... settings) {
            settingsDao.insert(settings[0]);
            return null;
        }

    }

    private static class updateSettingsAsyncTask extends AsyncTask<Settings, Void, Void> {
        private SettingsDao settingsDao;
        private updateSettingsAsyncTask(SettingsDao settingsDao) {
            this.settingsDao = settingsDao;
        }

        @Override
        protected Void doInBackground(Settings... settings) {
            settingsDao.update(settings[0]);
            return null;
        }

    }
}
