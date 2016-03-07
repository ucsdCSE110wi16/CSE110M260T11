package team11.crowded;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.Espresso.onData;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.core.StringStartsWith.startsWith;

import org.hamcrest.object.HasToString;
import org.hamcrest.core.StringStartsWith;


@RunWith(AndroidJUnit4.class)
public class ListLocationsTester {
    @Rule
    public ActivityTestRule<view_locations> mActivityRule = new ActivityTestRule<>(
            view_locations.class);

    @Before
    public void before(){}

    @Test
    public void SignIn(){
        //onView(withId(R.id.button)).perform(click());
        onData(hasToString(startsWith("Biomedical")))
                .inAdapterView(withId(R.id.listView))
                .perform(click());
    }

}
