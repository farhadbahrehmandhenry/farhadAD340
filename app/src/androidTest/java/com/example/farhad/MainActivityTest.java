package com.example.farhad;

import android.widget.DatePicker;

import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void hadProjectText() {
        onView(withId(R.id.projectName)).check(matches(withText(R.string.bahrehmand_farhad_04_30_2020)));
    }

    @Test
    public void validatesEmptyName() {
        onView(withId(R.id.name)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.signup)).perform(click());

        MainActivity activity = activityTestRule.getActivity();
        onView(withText("name is required")).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

//    @Test
//    public void validatesEmail() {
//        onView(withId(R.id.name)).perform(typeText("farhad"), closeSoftKeyboard());
//        onView(withId(R.id.email)).perform(typeText("farhad"), closeSoftKeyboard());
//        onView(withId(R.id.signup)).perform(click());
//
//        SignupActivity activity = activityTestRule.getActivity();
//        onView(withText("email is in wrong format")).
//                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
//                check(matches(isDisplayed()));
//    }

//    @Test
//    public void validatesUsername() {
//        onView(withId(R.id.name)).perform(typeText("farhad"), closeSoftKeyboard());
//        onView(withId(R.id.email)).perform(typeText("farhad@gmail.com"), closeSoftKeyboard());
//        onView(withId(R.id.username)).perform(typeText(""), closeSoftKeyboard());
//        onView(withId(R.id.signup)).perform(click());
//
//        SignupActivity activity = activityTestRule.getActivity();
//        onView(withText("username is required")).
//                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
//                check(matches(isDisplayed()));
//    }

    @Test
    public void validatesAge() {
        onView(withId(R.id.name)).perform(typeText("farhad"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("farhad@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.username)).perform(typeText("farhad1982"), closeSoftKeyboard());
        onView(withId(R.id.age)).perform(typeText(String.valueOf("")), closeSoftKeyboard());

        onView(withId(R.id.signup)).perform(click());

        MainActivity activity = activityTestRule.getActivity();
        onView(withText("age is required")).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    @Test
    public void notTooYoung() {
        onView(withId(R.id.name)).perform(typeText("farhad"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("farhad@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.username)).perform(typeText("farhad1982"), closeSoftKeyboard());
        onView(withId(R.id.age)).perform(typeText(String.valueOf("17")), closeSoftKeyboard());

        onView(withId(R.id.signup)).perform(click());

        MainActivity activity = activityTestRule.getActivity();
        onView(withText("Sorry!! you are too young")).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    @Test
    public void notTooOld() {
        onView(withId(R.id.name)).perform(typeText("farhad"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("farhad@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.username)).perform(typeText("farhad1982"), closeSoftKeyboard());
        onView(withId(R.id.age)).perform(typeText(String.valueOf("121")), closeSoftKeyboard());

        onView(withId(R.id.signup)).perform(click());

        MainActivity activity = activityTestRule.getActivity();
        onView(withText("too old")).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    @Test
    public void validatesEmptyOccupation() {
        onView(withId(R.id.name)).perform(typeText("farhad"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("farhad@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.username)).perform(typeText("farhad1982"), closeSoftKeyboard());
        onView(withId(R.id.age)).perform(typeText(String.valueOf("20")), closeSoftKeyboard());
        onView(withId(R.id.description)).perform(typeText(String.valueOf("hellooooooo")), closeSoftKeyboard());
        onView(withId(R.id.occupation)).perform(typeText(String.valueOf("")), closeSoftKeyboard());

        onView(withId(R.id.signup)).perform(click());

        MainActivity activity = activityTestRule.getActivity();
        onView(withText("occupation is required")).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    @Test
    public void validatesEmptyDescription() {
        onView(withId(R.id.name)).perform(typeText("farhad"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("farhad@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.username)).perform(typeText("farhad1982"), closeSoftKeyboard());
        onView(withId(R.id.age)).perform(typeText(String.valueOf("20")), closeSoftKeyboard());
        onView(withId(R.id.description)).perform(typeText(String.valueOf("")), closeSoftKeyboard());

        onView(withId(R.id.signup)).perform(click());

        MainActivity activity = activityTestRule.getActivity();
        onView(withText("description is required")).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    @Test
    public void validatesEmptyDOB() {
        onView(withId(R.id.name)).perform(typeText("farhad"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("farhad@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.username)).perform(typeText("farhad1982"), closeSoftKeyboard());
        onView(withId(R.id.age)).perform(typeText(String.valueOf("20")), closeSoftKeyboard());
        onView(withId(R.id.occupation)).perform(typeText(String.valueOf("programmer")), closeSoftKeyboard());
        onView(withId(R.id.description)).perform(typeText(String.valueOf("hellooooo")), closeSoftKeyboard());
        onView(withId(R.id.dateOfBirthText)).perform(typeText(String.valueOf("")), closeSoftKeyboard());

        onView(withId(R.id.signup)).perform(click());

        MainActivity activity = activityTestRule.getActivity();
        onView(withText("date of birth is required")).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    @Test
    public void validatesInvalidDOB() {
        onView(withId(R.id.name)).perform(typeText("farhad"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("farhad@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.username)).perform(typeText("farhad1982"), closeSoftKeyboard());
        onView(withId(R.id.age)).perform(typeText(String.valueOf("20")), closeSoftKeyboard());
        onView(withId(R.id.occupation)).perform(typeText(String.valueOf("programmer")), closeSoftKeyboard());
        onView(withId(R.id.description)).perform(typeText(String.valueOf("hellooooo")), closeSoftKeyboard());
        onView(withId(R.id.dateOfBirthText)).perform(typeText(String.valueOf("20204568")), closeSoftKeyboard());

        onView(withId(R.id.signup)).perform(click());

        MainActivity activity = activityTestRule.getActivity();
        onView(withText("seems one of the fields have wrong format")).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    @Test
    public void ageShouldMatchDOB() {
        onView(withId(R.id.name)).perform(typeText("farhad"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("farhad@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.username)).perform(typeText("farhad1982"), closeSoftKeyboard());
        onView(withId(R.id.age)).perform(typeText(String.valueOf("20")), closeSoftKeyboard());
        onView(withId(R.id.occupation)).perform(typeText(String.valueOf("programmer")), closeSoftKeyboard());
        onView(withId(R.id.description)).perform(typeText(String.valueOf("hellooooo")), closeSoftKeyboard());
        onView(withId(R.id.dateOfBirthText)).perform(typeText(String.valueOf("04-07-2001")), closeSoftKeyboard());

        onView(withId(R.id.signup)).perform(click());

        MainActivity activity = activityTestRule.getActivity();
        onView(withText("your age is not matched with your date of birth, calendar says you are 19")).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    @Test
    public void leadsToProfilePageWithCorrectData() {
        onView(withId(R.id.name)).perform(typeText("farhad"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("farhad@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.username)).perform(typeText("farhad1982"), closeSoftKeyboard());
        onView(withId(R.id.age)).perform(typeText(String.valueOf("37")), closeSoftKeyboard());
        onView(withId(R.id.occupation)).perform(typeText(String.valueOf("programmer")), closeSoftKeyboard());
        onView(withId(R.id.description)).perform(typeText(String.valueOf("hellooooo")), closeSoftKeyboard());
        onView(withId(R.id.dateOfBirthText)).perform(typeText("06-10-1982"), closeSoftKeyboard());

        try {
            Intents.init();
            onView(withId(R.id.signup)).perform(click());
            intended(hasComponent(ProfileActivity.class.getName()));
            intended(hasExtra(Constant.KEY_USERNAME, "farhad1982"));
            intended(hasExtra(Constant.KEY_NAME, "farhad"));
            intended(hasExtra(Constant.KEY_AGE, "37"));
            intended(hasExtra(Constant.KEY_DATE_OF_BIRTH, "06-10-1982"));
        } finally {
            Intents.release();
        }
    }

    @Test
    public void testShiftRepeaterTaskToTomorrow() {
        onView(withId(R.id.dateOfBirth)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).check(matches(isDisplayed()));
    }
}
