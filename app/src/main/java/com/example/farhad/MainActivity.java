package com.example.farhad;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    private EditText name;
    private EditText email;
    private EditText username;
    private EditText age;
    private EditText dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        age = findViewById(R.id.age);
        dob = findViewById(R.id.dateOfBirthText);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        name.setText("");
        email.setText("");
        username.setText("");
        age.setText("");
        dob.setText("");

        name.requestFocus();
    }

    protected void onResume() {
        super.onResume();

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
            StringBuilder msg = new StringBuilder(fieldName).append(" is required");

            Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 100);
            toast.show();
        }
        else {
            if (fieldName == "email" && !Patterns.EMAIL_ADDRESS.matcher(field).matches()) {
                StringBuilder msg = new StringBuilder(fieldName).append(" is in wrong format");
                Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);

                toast.setGravity(Gravity.TOP, 0, 100);
                toast.show();
            }
            if (fieldName == "age") {
                boolean isValidAge = true;

                try {
                    Integer.parseInt(field);
                    isValidAge = true;
                }
                catch (NumberFormatException ex){
                    StringBuilder msg = new StringBuilder(fieldName).append(" should be a number");
                    Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);

                    toast.setGravity(Gravity.TOP, 0, 100);
                    toast.show();
                    isValidAge = false;
                }

                if (isValidAge) {
                    int age =Integer.parseInt(field);

                    if (age < 18) {
                        Toast toast = Toast.makeText(
                                this,
                                "Sorry!! you are too young",
                                Toast.LENGTH_SHORT
                        );

                        toast.setGravity(Gravity.TOP, 0, 100);
                        toast.show();
                    }
                    else if (age > 120) {
                        Toast toast = Toast.makeText(
                                this,
                                "Wow!!! you look much younger, are you sure about your age",
                                Toast.LENGTH_SHORT
                        );
                        toast.setGravity(Gravity.TOP, 0, 100);
                        toast.show();
                    }
                    else {
                        isValid = true;
                    }
                }
            }
            if (fieldName == "date of birth") {
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDateTime now = LocalDateTime.now();

                String[] today = dateFormat.format(now).split("/", 3);
                String[] previousDate = field.split("-", 3);

                String todayString = "";
                String previousDateString = "";

                if (previousDate.length == 3) {
                    for (int i = 2; i >= 0; i--) {
                        if (today[i].length() < 2) {
                            StringBuilder d = new StringBuilder("0").append(today[i]);

                            todayString += d ;
                        }
                        else {
                            todayString += today[i];
                        }

                        if (previousDate[i].length() < 2) {
                            StringBuilder d = new StringBuilder("0").append(previousDate[i]);

                            previousDateString += d;
                        }
                        else {
                            previousDateString += previousDate[i];
                        }
                    }

                    Double todayStringInt = Double.valueOf(todayString);
                    Double previousDateStringInt = Double.valueOf(previousDateString);
                    Double ageFromDateOfBirth = (todayStringInt - previousDateStringInt) / 10000;
                    Log.d("asdasdas", String.valueOf(previousDateStringInt));
                    Log.d("asdasdas", String.valueOf(ageFromDateOfBirth));

                    if (age.getText().length() > 0) {
                        if (Double.valueOf(String.valueOf(age.getText())) == Math.round(ageFromDateOfBirth)) {
                            isValid = true;
                        }
                        else {
                            StringBuilder msg = new StringBuilder(
                                    "your age is not matched with your date of birth, calendar says you are "
                            ).append(Math.round(ageFromDateOfBirth));

                            Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.TOP, 0, 100);
                            toast.show();
                        }
                    }
                }
                else {
                    isValid = false;

                    Toast toast =  Toast.makeText(this, "seems one of the fields have wrong format", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, 0, 100);
                    toast.show();
                }
            }
            else {
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
        boolean nameIsValid = isValid(nameField, "name");

        if (nameIsValid) emailIsValid = isValid(emailField, "email");
        if (emailIsValid) usernameIsValid = isValid(usernameField, "username");
        if (usernameIsValid) ageIsValid = isValid(ageField, "age");
        if (ageIsValid) dateOfBirthIsValid = isValid(dateOfBirthField, "date of birth");

        if (nameIsValid && emailIsValid && usernameIsValid && ageIsValid && dateOfBirthIsValid) {
            Intent intent = new Intent(this, WeekTwoShowReportActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(Constant.KEY_NAME, usernameField);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else {
            Toast toast = Toast.makeText(this, "you are close to sign up!!", Toast.LENGTH_SHORT);
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

        String date = String.join("-", monthString, dayString, yearString);

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
}
