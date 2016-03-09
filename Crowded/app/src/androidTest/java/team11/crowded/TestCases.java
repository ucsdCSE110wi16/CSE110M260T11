package team11.crowded;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.text.format.Time;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.core.StringStartsWith.startsWith;

@RunWith(AndroidJUnit4.class)
public class TestCases {

    @Rule
    public ActivityTestRule<login_screen> mActivityRule = new ActivityTestRule<>(
            login_screen.class);
    public ActivityTestRule<view_locations> mActivityRule2 = new ActivityTestRule<>(
            view_locations.class);
    public ActivityTestRule<view_page> mActivityRule3 = new ActivityTestRule<>(
            view_page.class);
    public ActivityTestRule<add_comment> mActivityRule4 = new ActivityTestRule<>(
            add_comment.class);

    @Before
    public void before(){}

    private void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_set_time() {
        System.out.println("Testing setTime");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm");

        Time now = new Time();
        now.setToNow();
        now.switchTimezone(now.getCurrentTimezone());
        int currHour = now.hour;
        int currYear = now.year;
        int currMonth = now.month + 1;
        int currDay = now.monthDay;

        String time = format.format(new Date());
        location_database.setTime(time).matches("more than a year");
    }

    @Test
    public void test_sign_in_no_name() {
        onView(withId(R.id.button)).perform(click());
        assertEquals(login_screen.get_name(), "Guest User");
        pause(1500);

    }

    @Test
    public void test_location_list() {
        onView(withId(R.id.button)).perform(click());
        pause(1500);

        ArrayList<String> testList = location_database.get_locations();
        assertEquals(testList.get(0), "Biomedical Library");
        assertEquals(testList.size(), 12);
        pause(1500);
    }

    @Test
    public void test_sign_in_guest() {
        onView(withId(R.id.userName)).perform(typeTextIntoFocusedView("Guest 123"));
        pause(1500);
        onView(withId(R.id.button)).perform(click());
        closeSoftKeyboard();
        assertEquals( login_screen.get_name(), "Guest 123" );
    }


}