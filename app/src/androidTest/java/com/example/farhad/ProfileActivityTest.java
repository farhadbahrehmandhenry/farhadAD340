package com.example.farhad;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

public class ProfileActivityTest {
    @Rule
    public ActivityTestRule<ProfileActivity> activityTestRule = new ActivityTestRule<>(ProfileActivity.class);

    @Test
    public void goesToMain() {
        onView(withId(R.id.backButton)).perform(click());

        ProfileActivity activity = activityTestRule.getActivity();

        assertTrue(activity.isFinishing());
    }

    @Test
    public void validatesOccupation() {
        onView(withId(R.id.occupation)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.nextButton)).perform(click());

        ProfileActivity activity = activityTestRule.getActivity();
        onView(withText("occupation is required")).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    @Test
    public void validatesDescription() {
        onView(withId(R.id.occupation)).perform(typeText("programmer"), closeSoftKeyboard());
        onView(withId(R.id.description)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.nextButton)).perform(click());

        ProfileActivity activity = activityTestRule.getActivity();
        onView(withText("description is required")).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }
}