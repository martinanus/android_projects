package com.example.android.twoactivitiesnew;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import androidx.test.rule.ActivityTestRule;



/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ActivityInputOutputTest {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>( MainActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.example.android.twoactivitiesnew", appContext.getPackageName());
    }

    /*it will fail because I put some extra condition to forbid passing empty string
    @Test
    public void activityLaunch(){
    onView(withId(R.id.button_main)).perform(click());
    onView(withId(R.id.text_header_second)).check(matches(isDisplayed()));

        onView(withId(R.id.button_second)).perform(click());
        onView(withId(R.id.text_header_main)).check(matches(isDisplayed()));

    }

     */
    @Test
    public void emptyText(){
        onView(withId(R.id.button_main)).perform(click());
        onView(withId(R.id.text_header_main)).check(matches(withText("Error!")));
    }

    @Test
    public void textInputOutput(){
        onView(withId(R.id.editText_main)).perform(typeText("This is a test."));
        onView(withId(R.id.button_main)).perform(click());
        onView(withId(R.id.text_message)).check(matches(withText("This is a test.")));
    }
}
