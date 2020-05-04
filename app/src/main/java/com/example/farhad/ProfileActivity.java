package com.example.farhad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final TextView name = findViewById(R.id.name);
        final TextView age = findViewById(R.id.age);
        final TextView occupation = findViewById(R.id.occupation);
        final TextView description = findViewById(R.id.description);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if (b != null) {
            if (b.containsKey(Constant.KEY_NAME)) {
                name.setText(b.getString(Constant.KEY_NAME));
            }
            if (b.containsKey(Constant.KEY_AGE)) {
                age.setText(b.getString(Constant.KEY_AGE));
            }
            if (b.containsKey(Constant.KEY_OCCUPATION)) {
                occupation.setText(b.getString(Constant.KEY_OCCUPATION));
            }
            if (b.containsKey(Constant.KEY_DESCRIPTION)) {
                description.setText(b.getString(Constant.KEY_DESCRIPTION));
            }
        }
    }

    public void backToProfileDetail(View view) {
        finish();
    }
}