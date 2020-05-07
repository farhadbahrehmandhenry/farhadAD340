package com.example.farhad;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;

public class ProfileActivityTest {
    @Rule
    public ActivityTestRule<ProfileActivity> activityTestRule = new ActivityTestRule<>(ProfileActivity.class);

//    @Test
//    public void goesToMain() {
//        onView(withId(R.id.backButton)).perform(click());
//
//        ProfileActivity activity = activityTestRule.getActivity();
//
//        assertTrue(activity.isFinishing());
//    }
//
//    @Test
//    public void hasImage() {
//        onView(withId(R.id.profileImage)).check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void hasName() {
//        Intent i = new Intent();
//        i.putExtra("name", "farhad");
//        activityTestRule.launchActivity(i);
//
//        onView(withId(R.id.name)).check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void hasAge() {
//        Intent i = new Intent();
//        i.putExtra("age", "20");
//        activityTestRule.launchActivity(i);
//
//        onView(withId(R.id.age)).check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void hasOccupation() {
//        Intent i = new Intent();
//        i.putExtra("occupation", "programmer");
//        activityTestRule.launchActivity(i);
//
//        onView(withId(R.id.occupation)).check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void hasDescription() {
//        Intent i = new Intent();
//        i.putExtra("description", "heloooo!!!");
//        activityTestRule.launchActivity(i);
//
//        onView(withId(R.id.description)).check(matches(isDisplayed()));
//    }
}