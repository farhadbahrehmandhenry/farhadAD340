package com.example.farhad;

import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class MatchesFragmentTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class, true, true);

//    @Before
//    public void setUp() {
//        onView(withId(R.id.generic_avatar)).perform(swipeLeft());
//    }

//    @Test
//    public void likeButton() throws InterruptedException {
//        Thread.sleep(20000);
//        onView(allOf(withId(R.id.likeButton), isDisplayingAtLeast(50)))
//                .check(matches(withContentDescription(R.string.UnlikedMsg)));
//        onView(allOf(withId(R.id.likeButton), isDisplayingAtLeast(50))).perform(click());
//        onView(allOf(withId(R.id.likeButton), isDisplayingAtLeast(50)))
//                .check(matches(withContentDescription(R.string.LikeMsg)));
//        onView(allOf(withId(R.id.likeButton), isDisplayingAtLeast(50))).perform(click());
//        onView(allOf(withId(R.id.likeButton), isDisplayingAtLeast(50)))
//                .check(matches(withContentDescription(R.string.UnlikedMsg)));
//    }
}