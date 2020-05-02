package com.example.farhad;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    private TextView greet;
    private TextView thankYou;
    private EditText occupation;
    private EditText description;

    String userName;
    String name;
    String age;
    String email;
    String dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        greet = findViewById(R.id.greet);
        thankYou = findViewById(R.id.thankYou);
        occupation = findViewById(R.id.occupation);
        description = findViewById(R.id.description);

        StringBuilder greetMsg = new StringBuilder(getString(R.string.hello));
        StringBuilder ThanksMsg = new StringBuilder(getString(R.string.thanks));

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if (b != null) {
            if (b.containsKey(Constant.KEY_USERNAME)) {
                userName = b.getString(Constant.KEY_USERNAME);
            }
            if (b.containsKey(Constant.KEY_NAME)) {
                name = b.getString(Constant.KEY_NAME);
            }
            if (b.containsKey(Constant.KEY_EMAIL)) {
                email = b.getString(Constant.KEY_EMAIL);
            }
            if (b.containsKey(Constant.KEY_AGE)) {
                age = b.getString(Constant.KEY_AGE);
            }
            if (b.containsKey(Constant.KEY_DATE_OF_BIRTH)) {
                dob = b.getString(Constant.KEY_DATE_OF_BIRTH);
            }
        }

        greetMsg.append(name).append(getString(R.string.exclamation));
        greet.setText(greetMsg);
        thankYou.setText(ThanksMsg);
    }

    public void backToForm(View view) {
        finish();
    }

    public boolean isValid(String field, String fieldName) {
        boolean isValid = false;

        if (TextUtils.isEmpty(field)) {
            StringBuilder msg = new StringBuilder(fieldName).append(getString(R.string.required));

            Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 100);
            toast.show();
        } else {
            isValid = true;
        }

        return isValid;
    }

    public void toDetails(View view) {
        String occupationField = String.valueOf(occupation.getText()).trim();
        String descriptionField = String.valueOf(description.getText()).trim();

        boolean occupationIsValid = isValid(occupationField, Constant.KEY_OCCUPATION);
        boolean descriptionIsValid = false;

        if (occupationIsValid) descriptionIsValid = isValid(descriptionField, Constant.KEY_DESCRIPTION);

        if (occupationIsValid && descriptionIsValid) {
            Intent intent = new Intent(this, ProfileDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(Constant.KEY_USERNAME, userName);
            bundle.putString(Constant.KEY_NAME, name);
            bundle.putString(Constant.KEY_EMAIL, email);
            bundle.putString(Constant.KEY_AGE, age);
            bundle.putString(Constant.KEY_DATE_OF_BIRTH, dob);
            bundle.putString(Constant.KEY_OCCUPATION, occupationField);
            bundle.putString(Constant.KEY_DESCRIPTION, descriptionField);

            intent.putExtras(bundle);

            startActivity(intent);
        }
    }
}