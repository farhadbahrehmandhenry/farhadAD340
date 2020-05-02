package com.example.farhad;

import android.content.Intent;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertTrue;

public class ProfileDetailActivityTest {
    @Rule
    public ActivityTestRule<ProfileDetailActivity> activityTestRule = new ActivityTestRule<>(ProfileDetailActivity.class);

    @Test
    public void goesToMain() {
        onView(withId(R.id.backButton)).perform(click());

        ProfileDetailActivity activity = activityTestRule.getActivity();

        assertTrue(activity.isFinishing());
    }

    @Test
    public void hasImage() {
        onView(withId(R.id.profileImage)).check(matches(isDisplayed()));
    }

    @Test
    public void hasName() {
        Intent i = new Intent();
        i.putExtra("name", "farhad");
        activityTestRule.launchActivity(i);

        onView(withId(R.id.name)).check(matches(isDisplayed()));
    }

    @Test
    public void hasAge() {
        Intent i = new Intent();
        i.putExtra("age", "20");
        activityTestRule.launchActivity(i);

        onView(withId(R.id.age)).check(matches(isDisplayed()));
    }

    @Test
    public void hasOccupation() {
        Intent i = new Intent();
        i.putExtra("occupation", "programmer");
        activityTestRule.launchActivity(i);

        onView(withId(R.id.occupation)).check(matches(isDisplayed()));
    }

    @Test
    public void hasDescription() {
        Intent i = new Intent();
        i.putExtra("description", "heloooo!!!");
        activityTestRule.launchActivity(i);

        onView(withId(R.id.description)).check(matches(isDisplayed()));
    }
}