package com.example.farhad;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToWeekOne(View view) {
        Intent intent = new Intent(this, WeekOneActivity.class);
        startActivity(intent);
    }

    public void goToWeekTwo(View view) {
        Intent intent = new Intent(this, WeekTwoActivity.class);
        startActivity(intent);
    }
}
