package com.example.farhad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class WeekTwoActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    private EditText name;
    private EditText email;
    private EditText username;
    private EditText age;
    private TextView dateOfBirthText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_two);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        age = findViewById(R.id.age);
        dateOfBirthText = findViewById(R.id.dateOfBirthText);
    }

    public boolean isValid(String field, String fieldName) {
        boolean isValid = false;

        if (TextUtils.isEmpty(field)) {
            StringBuilder msg = new StringBuilder(fieldName).append(" is required");
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
        else {
            if (fieldName == "email" && !Patterns.EMAIL_ADDRESS.matcher(field).matches()) {
                StringBuilder msg = new StringBuilder(fieldName).append(" is in wrong format");
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            }
            if (fieldName == "age") {
                boolean isValidAge = true;

                try {
                    Integer.parseInt(field);
                    isValidAge = true;
                }
                catch (NumberFormatException ex){
                    StringBuilder msg = new StringBuilder(fieldName).append(" should be a number");
                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

                    isValidAge = false;
                }

                if (isValidAge) {
                    int age =Integer.parseInt(field);

                    if (age < 18) {
                        Toast.makeText(
                                this,
                                "Sorry!! you are too young",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                    else if (age > 120) {
                        Toast.makeText(
                                this,
                                "Wow!!! you look much younger, are you sure about your age",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                    else {
                        isValid = true;
                    }
                }
            }
            if (fieldName == "dateOfBirth") {
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDateTime now = LocalDateTime.now();

                String[] today = dateFormat.format(now).split("/", 3);
                String[] previousDate = field.split("-", 3);

                String todayString = "";
                String previousDateString = "";


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

                Integer todayStringInt = Integer.valueOf(todayString);
                Integer previousDateStringInt = Integer.valueOf(previousDateString);
                Integer ageFromDateOfBirth = (todayStringInt - previousDateStringInt) / 10000;

                if (age.getText().length() > 0) {
                    if (Integer.valueOf(String.valueOf(age.getText())) == ageFromDateOfBirth) {
                        isValid = true;
                    }
                    else {
                        Toast.makeText(
                                this,
                                "your age is not matched with your date of birth",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
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
        String dateOfBirthField = String.valueOf(dateOfBirthText.getText()).trim();

        boolean emailIsValid = false;
        boolean usernameIsValid = false;
        boolean ageIsValid = false;
        boolean dateOfBirthIsValid = false;
        boolean nameIsValid = isValid(nameField, "name");

        if (nameIsValid) emailIsValid = isValid(emailField, "email");
        if (emailIsValid) usernameIsValid = isValid(usernameField, "username");
        if (usernameIsValid) ageIsValid = isValid(ageField, "age");
        if (ageIsValid) dateOfBirthIsValid = isValid(dateOfBirthField, "dateOfBirth");

        if (nameIsValid && emailIsValid && usernameIsValid && ageIsValid && dateOfBirthIsValid) {
            Intent intent = new Intent(this, WeekTwoShowReportActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(Constant.KEY_NAME, usernameField);
            intent.putExtras(bundle);
            startActivity(intent);
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
        String date = String.join(
                "-",
                Integer.toString(dayOfMonth),
                Integer.toString(month),
                Integer.toString(year)
        );

        dateOfBirthText.setText(date);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.containsKey(Constant.KEY_DATE_OF_BIRTH)) {
            dateOfBirthText.setText(savedInstanceState.getString(Constant.KEY_DATE_OF_BIRTH));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(Constant.KEY_DATE_OF_BIRTH, dateOfBirthText.getText().toString());
    }
}