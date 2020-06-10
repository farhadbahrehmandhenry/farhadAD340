package com.example.farhad;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class SettingsFragment extends Fragment {
    private EditText maxDistance;
    private TimePicker reminderTimePicker;
    private String reminderTimeString;
    private Spinner gender;
    private Spinner lookingForGender;
    private Spinner accountPrivacy;
    private EditText minAge;
    private EditText maxAge;
    private Button saveSettingsButton;
    private Settings userSettings = new Settings();
    private SettingsViewModel vm;
    private static final String TAG = SettingsFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        Context context = getContext();
        userSettings = new Settings();
        vm = new ViewModelProvider(this).get(SettingsViewModel.class);

        maxDistance = view.findViewById(R.id.editText_max_distance);

        gender = view.findViewById(R.id.spinner_gender);

        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(context,
                R.array.gender, android.R.layout.simple_spinner_item);

        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        gender.setAdapter(genderAdapter);

        lookingForGender = view.findViewById(R.id.spinner_looking_for_gender);

        ArrayAdapter<CharSequence> lookingForGenderAdapter = ArrayAdapter.createFromResource(context,
                R.array.gender, android.R.layout.simple_spinner_item);

        lookingForGenderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        lookingForGender.setAdapter(lookingForGenderAdapter);

        accountPrivacy = view.findViewById(R.id.accountType);

        ArrayAdapter<CharSequence> accountPrivacyAdapter = ArrayAdapter.createFromResource(context,
                R.array.privacy, android.R.layout.simple_spinner_item);

        accountPrivacyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        accountPrivacy.setAdapter(accountPrivacyAdapter);

        minAge = view.findViewById(R.id.editText_min_age);
        maxAge = view.findViewById(R.id.editText_max_age);

        reminderTimePicker = view.findViewById(R.id.reminder_time_picker);
        reminderTimePicker.setIs24HourView(true);
        reminderTimePicker.setHour(12);
        reminderTimePicker.setMinute(0);
        reminderTimeString = "00:00";
        reminderTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                String reminderHour = String.format("%02d", hourOfDay);
                String reminderMinute = String.format("%02d", minute);
                reminderTimeString = reminderHour + ":" + reminderMinute;
            }
        });

        saveSettingsButton = view.findViewById(R.id.button_save_settings);
        saveSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveSettingsSubmit(v);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Activity rootView = getActivity();
        Intent incomingIntent = rootView.getIntent();
        Bundle incomingState = incomingIntent.getExtras();

        if (incomingState != null) {
            Bundle incomingExtras = incomingState.getBundle(Constant.KEY_USER_DATA);

            if (incomingExtras != null) {
                if (incomingExtras.containsKey(Constant.KEY_EMAIL)) {
                    String email = incomingExtras.getString(Constant.KEY_EMAIL);
                    vm.findSettingsByEmail(email).observe(getViewLifecycleOwner(), new Observer<Settings>() {
                        @Override
                        public void onChanged(@Nullable final Settings userSettingsFromDb) {
                            userSettings = userSettingsFromDb;
                            if (userSettings == null) {
                                userSettings = new Settings();
                                userSettings.setEmail(email);
                            }
                            Log.e(TAG, "from database, gender is " + userSettings.getGender());
                            loadSettingsIntoForm();
                        }
                    });
                }
            }
        }
    }

    public void loadSettingsIntoForm() {
        if (userSettings == null) {
            userSettings = new Settings();
        }
        maxDistance.setText(Integer.toString(userSettings.getMaxDistance()));

        if (!userSettings.getReminderTime().equals("")) { reminderTimeString = userSettings.getReminderTime(); }
        int hour = Integer.parseInt(reminderTimeString.substring(0, 2));
        reminderTimePicker.setHour(hour);
        int minute = Integer.parseInt(reminderTimeString.substring(3, 4));
        reminderTimePicker.setMinute(minute);

        int genderPosition = 0;
        String currentGender = userSettings.getGender();
        String gender1 = getResources().getStringArray(R.array.gender)[1];
        String gender2 = getResources().getStringArray(R.array.gender)[2];

        if (currentGender.equals(gender1)) { genderPosition = 1; }
        else if (currentGender.equals(gender2)) { genderPosition = 2; }
        gender.setSelection(genderPosition);

        int lookingForGenderPosition = 0;
        String currentLookingForGender = userSettings.getLookingForGender();
        if (currentLookingForGender.equals(gender1)) { lookingForGenderPosition = 1; }
        else if (currentLookingForGender.equals(gender2)) { lookingForGenderPosition = 2; }
        lookingForGender.setSelection(lookingForGenderPosition);

        int privacyPosition;
        if (userSettings.getPrivateAccount()) { privacyPosition = 0; }
        else { privacyPosition = 1; }
        accountPrivacy.setSelection(privacyPosition);

        if ((Integer) userSettings.getMinAge() != null && userSettings.getMinAge() != 0) {
            minAge.setText(Integer.toString(userSettings.getMinAge()));
        }
        if ((Integer) userSettings.getMaxAge() != null && userSettings.getMaxAge() != 0) {
            maxAge.setText(Integer.toString(userSettings.getMaxAge()));
        }
    }

    private void onSaveSettingsSubmit(View view) {
        if (view == getActivity().findViewById(R.id.button_save_settings)) {
            int maxDist = Integer.parseInt(maxDistance.getText().toString());

            String minAgeString = minAge.getText().toString();
            int minAgeVal = 18;
            minAgeVal = Integer.parseInt(minAgeString);

            String maxAgeString = maxAge.getText().toString();

            int maxAgeVal = 99;
            maxAgeVal = Integer.parseInt(maxAgeString);

            userSettings.setMaxDistance(maxDist);
            userSettings.setMinAge(minAgeVal);
            userSettings.setMaxAge(maxAgeVal);
            userSettings.setGender(gender.getSelectedItem().toString());
            userSettings.setLookingForGender(lookingForGender.getSelectedItem().toString());
            boolean accountIsPrivate = accountPrivacy.getSelectedItem().toString()
                    .equals(getResources().getStringArray(R.array.privacy)[0]);
            userSettings.setPrivateAccount(accountIsPrivate);
            if (reminderTimeString != null) {
                userSettings.setReminderTime(reminderTimeString);
            }

            vm.insert(userSettings);
            loadSettingsIntoForm();

            Toast toast = Toast.makeText(getContext(), "Settings Saved", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}