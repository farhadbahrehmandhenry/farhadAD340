package com.example.farhad;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.NumberPicker;

import androidx.appcompat.app.AppCompatActivity;

public class AddSettingsActivity extends AppCompatActivity {
    public static final String Extra_REMINDER = "com.example.farhad.Extra_TITLE";
    public static final String Extra_DISTANCE = "com.example.farhad.Extra_DISTANCE";
    public static final String Extra_GENDER = "com.example.farhad.Extra_GENDER";
    public static final String Extra_ACCOUNT = "com.example.farhad.Extra_ACCOUNT";
    public static final String Extra_AGEGENDER = "com.example.farhad.Extra_AGEGENDER";

    private NumberPicker reminder;
    private NumberPicker distance;
    private NumberPicker gender;
    private NumberPicker account;
    private NumberPicker ageRange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_settings);

        reminder = findViewById(R.id.reminder);
        distance = findViewById(R.id.distance);
        gender = findViewById(R.id.gender);
        account = findViewById(R.id.accountType);
        ageRange = findViewById(R.id.ageRange);

        reminder.setMinValue(0);
        reminder.setMinValue(24);

        distance.setMinValue(1);
        distance.setMinValue(20);

        gender.setMinValue(0);
        gender.setMinValue(1);

        account.setMinValue(0);
        account.setMinValue(1);

        ageRange.setMinValue(18);
        ageRange.setMinValue(100);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Note");
    }

    private void saveSettings() {
        int r = reminder.getValue();
        int d = distance.getValue();
        int g = gender.getValue();
        int a = account.getValue();
        int age = ageRange.getValue();

        Intent data = new Intent();
        data.putExtra(Extra_REMINDER, r);
        data.putExtra(Extra_DISTANCE, d);
        data.putExtra(Extra_GENDER, g);
        data.putExtra(Extra_ACCOUNT, a);
        data.putExtra(Extra_AGEGENDER, age);

        setResult(RESULT_OK, data);

        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_settings_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_settings:
                saveSettings();
                return true;
            default:
                return  super.onOptionsItemSelected(item);
        }
    }

}
