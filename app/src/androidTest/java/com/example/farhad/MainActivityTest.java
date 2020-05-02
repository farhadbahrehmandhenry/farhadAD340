package com.example.farhad;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void hadProjectText() {
        onView(withId(R.id.projectName)).check(matches(withText(R.string.bahrehmand_farhad_04_30_2020)));
    }

    @Test
    public void goesToSignup() {
        try {
            Intents.init();
            onView(withId(R.id.goToSignup)).perform(click());
            intended(hasComponent(SignupActivity.class.getName()));
        } finally {
            Intents.release();
        }
    }
}
