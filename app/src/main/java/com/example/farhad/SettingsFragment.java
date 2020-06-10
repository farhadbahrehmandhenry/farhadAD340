package com.example.farhad;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class SettingsFragment extends Fragment {
    EditText distance;
    EditText ageRange;
    Spinner reminder;
    Spinner privacy;
    Spinner gender;
    Button save;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle userInfo = getArguments();
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        SettingsViewModel settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        assert userInfo != null;

        String email = userInfo.getString(Constant.KEY_EMAIL);

        distance = view.findViewById(R.id.etDistance);
        ageRange = view.findViewById(R.id.etAgeRange);
        reminder = view.findViewById(R.id.sReminder);
        privacy = view.findViewById(R.id.sPrivacy);
        gender = view.findViewById(R.id.sGender);
        save = view.findViewById(R.id.btn_save_settings);

        ArrayAdapter<CharSequence> reminderAdapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.reminder, android.R.layout.simple_spinner_item);
        reminderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reminder.setAdapter(reminderAdapter);

        ArrayAdapter<CharSequence> privacyAdapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.privacy, android.R.layout.simple_spinner_item);
        privacyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        privacy.setAdapter(privacyAdapter);

        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.gender, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(genderAdapter);

        final Observer<List<Settings>> getSettingsObserver = newSettings -> {
            if (newSettings == null || newSettings.size() <= 0) {
                return;
            }

            Settings settings = newSettings.get(0);

            if (settings == null) {
                return;
            }

            distance.setHint(Integer.toString(settings.getDistance()));
            ageRange.setHint(Integer.toString(settings.getAgeRange()));
            reminder.setSelection(reminderAdapter.getPosition(settings.getReminder()));
            privacy.setSelection(privacyAdapter.getPosition(settings.getPrivacy()));
            gender.setSelection(genderAdapter.getPosition(settings.getGender()));

        };

        save.setOnClickListener(v -> {
            Settings settings = new Settings();
            settings.setEmail(email);

            int distanceInt;

            if (distance.getText().toString().equals(""))
                distanceInt = 10;
            else
                distanceInt = Integer.parseInt(distance.getText().toString());

            int ageInt;
            if (ageRange.getText().toString().equals(""))
                ageInt = 18;
            else
                ageInt = Integer.parseInt(ageRange.getText().toString());

            ArrayList<String> errors = new ArrayList<>();

            if (gender.getSelectedItem().equals(""))
                errors.add(getString(R.string.required));
            else
                settings.setGender((String) gender.getSelectedItem());
            if (ageInt < 0 || ageInt > 99)
                errors.add(getString(R.string.required));
            else
                settings.setAgeRange(ageInt);
            if (distanceInt < 0 || distanceInt > 9999)
                errors.add(getString(R.string.required));
            else
                settings.setDistance(distanceInt);

            settings.setReminder((String) reminder.getSelectedItem());
            settings.setPrivacy((String) privacy.getSelectedItem());

            if (errors.size() > 0) {
                String errorToast = String.join("", errors);
                Toast.makeText(v.getContext(), errorToast, Toast.LENGTH_LONG).show();
            } else {
                settingsViewModel.insertSettings(view.getContext(), settings);
                Toast.makeText(v.getContext(), R.string.save, Toast.LENGTH_SHORT).show();
            }
        });

        String[] emails = { email };
        settingsViewModel.loadSettingsByEmail(view.getContext(), emails).observe((LifecycleOwner) view.getContext(), getSettingsObserver);

        return view;
    }
}