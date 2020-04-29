package com.example.farhad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WeekTwoShowReportActivity extends AppCompatActivity {
    private TextView report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_two_show_report);

        report = findViewById(R.id.report);
        StringBuilder msg = new StringBuilder(getString(R.string.thanks));
        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        String userName = "";

        if (b != null) {
            if (b.containsKey(Constant.KEY_USERNAME)) {
                userName = b.getString(Constant.KEY_USERNAME);
            }
        }

        msg.append(userName).append(getString(R.string.exclamation));
        report.setText(msg);
    }

    public void backToForm(View view) {
        finish();
    }
}