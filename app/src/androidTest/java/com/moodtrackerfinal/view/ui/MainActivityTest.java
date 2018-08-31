package com.moodtrackerfinal.view.ui;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;
import com.moodtrackerfinal.R;
public class MainActivityTest
{
    // Test rule to launch MainActivity
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private MainActivity mainActivity = null;
    // Monitor for history activity
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(HistoryActivity.class.getName(), null, false);

    @Before
    public void setUp() throws Exception
    {
        mainActivity = mainActivityActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch()
    {
        View view = mainActivity.findViewById(R.id.mood_smiley);
        assertNotNull(view);
    }
    @Test
    public void testLaunchHistoryActivity()
    {
        assertNotNull(mainActivity.findViewById(R.id.show_history));
        onView(withId(R.id.show_history)).perform(click());
        Activity historyActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(historyActivity);
        historyActivity.finish();
    }
    @After
    public void tearDown() throws Exception
    {
        mainActivity = null;
    }
}