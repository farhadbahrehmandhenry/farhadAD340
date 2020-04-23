package com.example.farhad;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WeekTwoShowReportActivity extends AppCompatActivity {
    private TextView report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_two_show_report);

        report = findViewById(R.id.report);
        StringBuilder msg = new StringBuilder("Thanks for Signing Up ");
        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        String userName = "";

        if (b != null) {
            if (b.containsKey(Constant.KEY_NAME)) {
                userName = b.getString(Constant.KEY_NAME);
            }
        }

        msg.append(userName).append("!");
        report.setText(msg);
    }

    public void backToForm(View view) {
        Intent intent = new Intent(this, WeekTwoActivity.class);

        startActivity(intent);
    }
}