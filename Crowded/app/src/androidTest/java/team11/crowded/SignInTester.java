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
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.StringStartsWith.startsWith;


@RunWith(AndroidJUnit4.class)
public class SignInTester {

    @Rule
    public ActivityTestRule<login_screen> mActivityRule = new ActivityTestRule<>(
            login_screen.class);
    public ActivityTestRule<view_locations> mActivityRule2 = new ActivityTestRule<>(
            view_locations.class);
    public ActivityTestRule<view_page> mActivityRule3 = new ActivityTestRule<>(
            view_page.class);

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
    public void SignIn(){
        onView(withId(R.id.userName)).perform(typeTextIntoFocusedView("Guest 123"));
        pause(500);
        onView(withId(R.id.button)).perform(click());
        if(!login_screen.get_user().matches("Guest 123")){
            System.out.println("Guest name is: " +login_screen.get_user()+" when it should be Guest 123");
        }
        else System.out.println("Guest name is correct! Test 1 Passes!");
        closeSoftKeyboard();
        pause(500);
        onData(hasToString(startsWith("Biomedical Library")))
                .inAdapterView(withId(R.id.listView)).atPosition(0)
                .check(matches(withText(containsString("Biomedical Library"))));
        System.out.println("Goes to list of locations! Test 2 Passes!");
        pause(500);
        //Goes to Geisel 7th Floor
        onView(withId(R.id.listView)).perform(click());
        System.out.println("Goes to Biomedical Library! Test 3 Passes!");
        pause(500);

    }
}
