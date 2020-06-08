package com.example.farhad;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.farhad.Settings;
import com.example.farhad.SettingsViewModel;

import java.util.List;
import java.util.Objects;

public class SettingsFragment extends Fragment {
    private Integer settingsId = 1;
    private SettingsViewModel settingsViewModel;
    private Settings loadedSettings;
    private NumberPicker minAge;
    private NumberPicker maxAge;
    private Integer minAgeLimit = 18;
    private Integer maxAgeLimit = 120;
    private Integer defaultMinAge = 18;
    private Integer defaultMaxAge = 120;
    NumberPicker distance;
    EditText ageRange;
    TimePicker reminder;
    Switch privacy;
    Spinner gender;
    Button save;


    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment newInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);

        final Observer<Settings> getSettingsObserver = dbSettings -> {
            loadedSettings = dbSettings;

            if (loadedSettings == null) {
                loadedSettings = new Settings();
                loadedSettings.setId(1);
                loadedSettings.setMatchReminderHour(14);
                loadedSettings.setMatchReminderMin(0);
                loadedSettings.setPrivateAccount(false);
                loadedSettings.setMaxDistance(Settings.getMaxDistance());
                loadedSettings.setMinAge(18);
                loadedSettings.setMaxAge(120);
            }

            this.minAge.setValue(loadedSettings.getMinAge());
            this.maxAge.setValue(loadedSettings.getMaxAge());
        };

        settingsViewModel.loadSettingsById(this.getContext(), 1).observe(this, getSettingsObserver);
        settingsViewModel.loadSettingsById(this.getContext(), 1).getValue();
    }

    public void updateSettings(View view, Settings settings) {
        settingsViewModel.updateSettings(this.getContext(), settings);
        Toast.makeText(view.getContext(), R.string.Updated, Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        distance = view.findViewById(R.id.distance_value);
        ageRange = view.findViewById(R.id.ageRange);
        reminder = view.findViewById(R.id.match_reminder_setting_Id);
        privacy = view.findViewById(R.id.accountType);
        gender = view.findViewById(R.id.gender);
        save = view.findViewById(R.id.save);
        maxAge = view.findViewById(R.id.max_age_value);
        minAge = view.findViewById(R.id.min_age_value);

        this.minAge = view.findViewById(R.id.min_age_value);
        this.minAge.setMinValue(minAgeLimit);
        this.minAge.setMaxValue(maxAgeLimit);

        this.maxAge = view.findViewById(R.id.max_age_value);
        this.maxAge.setMinValue(minAgeLimit);
        this.maxAge.setMaxValue(maxAgeLimit);

        this.minAge.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                loadedSettings.setMinAge(minAge.getValue());
                updateSettings(view, loadedSettings);
            }
        });

        this.maxAge.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                loadedSettings.setMaxAge(maxAge.getValue());
                updateSettings(view, loadedSettings);
            }
        });

        final Observer<List<Settings>> getSettingsObserver = newSettings -> {
            if (newSettings == null || newSettings.size() <= 0) {
                return;
            }

            Settings settings = newSettings.get(0);

            if (settings == null) {
                return;
            }
        };

        save.setOnClickListener(v -> {
            Settings settings = new Settings();

            if (privacy != null && reminder != null && distance != null && minAge != null && maxAge != null && gender != null) {
                settings.setPrivateAccount(privacy.isChecked());
                settings.setMatchReminderHour(reminder.getHour());
                settings.setMatchReminderMin(reminder.getMinute());
                settings.setMaxDistance(distance.getValue());
                settings.setMinAge(minAge.getValue());
                settings.setMaxAge(maxAge.getValue());
                settings.setGender(String.valueOf(gender.getSelectedItem()));
            }

            settingsViewModel.insertSettings(view.getContext(), settings);
        });

        return view;
    }
}