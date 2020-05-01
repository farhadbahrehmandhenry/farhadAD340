package com.example.farhad;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

public class SignupActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private EditText name;
    private EditText email;
    private EditText username;
    private EditText age;
    private EditText dob;
    private ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        age = findViewById(R.id.age);
        dob = findViewById(R.id.dateOfBirthText);
        button = findViewById(R.id.signup);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        button.setImageResource(R.drawable.play);

        name.setText("");
        email.setText("");
        username.setText("");
        age.setText("");
        dob.setText("");

        name.requestFocus();
    }

    public boolean isValid(String field, String fieldName) {
        boolean isValid = false;

        if (TextUtils.isEmpty(field)) {
            StringBuilder msg = new StringBuilder(fieldName).append(getString(R.string.required));

            Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 100);
            toast.show();
        } else {
            if (fieldName == Constant.KEY_EMAIL && !Patterns.EMAIL_ADDRESS.matcher(field).matches()) {
                validateEmail(fieldName);
            }
            if (fieldName == Constant.KEY_AGE) {
                validateAge(field, fieldName);
            }
            if (fieldName == getString(R.string.dob)) {
                String[] previousDate = field.split(getString(R.string.dash), 3);

                if (previousDate.length == 3) {
                    LocalDate t = LocalDate.now();
                    LocalDate birthday = LocalDate.of(
                            Integer.parseInt(previousDate[2]),
                            Integer.parseInt(previousDate[0]),
                            Integer.parseInt(previousDate[1])
                    );

                    Period p = Period.between(birthday, t);

                    if (age.getText().length() > 0) {
                        if (Double.valueOf(String.valueOf(age.getText())) == Math.round(p.getYears())) {
                            isValid = true;
                        } else {
                            isValid = false;

                            StringBuilder msg = new StringBuilder(getString(R.string.ageNotMatched)
                            ).append(p.getYears());

                            Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.TOP, 0, 100);
                            toast.show();
                        }
                    }
                }
                else {
                    isValid = false;

                    Toast toast = Toast.makeText(this, R.string.badFormat, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, 0, 100);
                    toast.show();
                }
            } else {
                isValid = true;
            }
        }

        return isValid;
    }

    public void validateInput(View view) {
        String nameField = String.valueOf(name.getText()).trim();
        String emailField = String.valueOf(email.getText()).trim();
        String usernameField = String.valueOf(username.getText()).trim();
        String ageField = String.valueOf(age.getText()).trim();
        String dateOfBirthField = String.valueOf(dob.getText()).trim();

        boolean emailIsValid = false;
        boolean usernameIsValid = false;
        boolean ageIsValid = false;
        boolean dateOfBirthIsValid = false;
        boolean nameIsValid = isValid(nameField, Constant.KEY_NAME);

        if (nameIsValid) emailIsValid = isValid(emailField, Constant.KEY_EMAIL);
        if (emailIsValid) usernameIsValid = isValid(usernameField, Constant.KEY_USERNAME);
        if (usernameIsValid) ageIsValid = isValid(ageField, Constant.KEY_AGE);
        if (ageIsValid) dateOfBirthIsValid = isValid(dateOfBirthField, getString(R.string.dob));

        if (nameIsValid && emailIsValid && usernameIsValid && ageIsValid && dateOfBirthIsValid) {
            button.setImageResource(R.drawable.play_green);
            Intent intent = new Intent(this, ProfileActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(Constant.KEY_USERNAME, usernameField);
            bundle.putString(Constant.KEY_NAME, nameField);
            bundle.putString(Constant.KEY_EMAIL, emailField);
            bundle.putString(Constant.KEY_AGE, ageField);
            bundle.putString(Constant.KEY_DATE_OF_BIRTH, dateOfBirthField);

            intent.putExtras(bundle);

            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(this, R.string.signup, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 100);
            toast.show();
        }
    }

    public void showCalendar(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String yearString = Integer.toString(year);
        String monthString = Integer.toString(month + 1);
        String dayString = Integer.toString(dayOfMonth);

        String date = String.join(getString(R.string.dash), monthString, dayString, yearString);

        dob.setText(date);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.containsKey(Constant.KEY_DATE_OF_BIRTH)) {
            dob.setText(savedInstanceState.getString(Constant.KEY_DATE_OF_BIRTH));
        }
        if (savedInstanceState.containsKey(Constant.KEY_NAME)) {
            name.setText(savedInstanceState.getString(Constant.KEY_NAME));
        }
        if (savedInstanceState.containsKey(Constant.KEY_EMAIL)) {
            email.setText(savedInstanceState.getString(Constant.KEY_EMAIL));
        }
        if (savedInstanceState.containsKey(Constant.KEY_USERNAME)) {
            username.setText(savedInstanceState.getString(Constant.KEY_USERNAME));
        }
        if (savedInstanceState.containsKey(Constant.KEY_AGE)) {
            age.setText(savedInstanceState.getString(Constant.KEY_AGE));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(Constant.KEY_DATE_OF_BIRTH, dob.getText().toString());
        outState.putString(Constant.KEY_NAME, name.getText().toString());
        outState.putString(Constant.KEY_EMAIL, email.getText().toString());
        outState.putString(Constant.KEY_USERNAME, username.getText().toString());
        outState.putString(Constant.KEY_AGE, age.getText().toString());
    }

    public void validateEmail(String fieldName) {
        StringBuilder msg = new StringBuilder(fieldName).append(getString(R.string.wrongFormat));
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);

        toast.setGravity(Gravity.TOP, 0, 100);
        toast.show();
    }

    public boolean validateAge(String field, String fieldName) {
        boolean isValidAge = true;
        boolean isValid = false;

        try {
            Integer.parseInt(field);
            isValidAge = true;
        } catch (NumberFormatException ex) {
            StringBuilder msg = new StringBuilder(fieldName).append(getString(R.string.shouldBeANumber));

            Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 100);
            toast.show();

            isValidAge = false;
        }

        if (isValidAge) {
            int age = Integer.parseInt(field);

            if (age < 18) {
                Toast toast = Toast.makeText(this, R.string.tooYoung, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 100);
                toast.show();
            } else if (age > 120) {
                Toast toast = Toast.makeText(
                        this,
                        R.string.tooOld,
                        Toast.LENGTH_SHORT
                );
                toast.setGravity(Gravity.TOP, 0, 100);
                toast.show();
            } else {
                isValid = true;
            }
        }

        return isValid;
    }

    public void backToHome(View view) {
        finish();
    }
}
