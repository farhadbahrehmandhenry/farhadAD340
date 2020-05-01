package com.example.farhad;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class ProfileActivityTest {
    @Rule
    public ActivityTestRule<SignupActivity> activityTestRule = new ActivityTestRule<>(SignupActivity.class);

    @Test
    public void goesToMain() {
        onView(withId(R.id.backButton)).perform(click());

        SignupActivity activity = activityTestRule.getActivity();

        assertTrue(activity.isFinishing());
    }
}