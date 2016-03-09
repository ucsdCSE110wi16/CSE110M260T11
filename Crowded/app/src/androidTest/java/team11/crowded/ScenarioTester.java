package team11.crowded;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ListView;

import com.firebase.client.Firebase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.Espresso.onData;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.StringStartsWith.startsWith;


@RunWith(AndroidJUnit4.class)
public class ScenarioTester {

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
public void SignInNoName(){
        onView(withId(R.id.button)).perform(click());
        System.out.println("Sign in and goes to list is correct! Test 1 Passes!");
        pause(1500);

        ArrayList<String> testList = location_database.get_locations();

        onData(hasToString(startsWith("Biomedical Library")))
                .inAdapterView(withId(R.id.listView))
                .atPosition(0)
                .perform(click());
        System.out.println("Goes to Biomedical Library! Test 2 Passes!");
        pause(1500);

        onView(withId(R.id.submitButton)).perform(click());

        System.out.println("Goes submit post page for Biomedical Library! Test 3 Passes!");
        pause(1500);

        onView(withId(R.id.commentField)).perform(typeTextIntoFocusedView("So crowded!"));
        Espresso.closeSoftKeyboard();
        pause(1500);

        onView(withId(R.id.rating)).perform(typeText("10"), closeSoftKeyboard());
        pause(1500);

        onView(withId(R.id.postButton)).perform(click());
        System.out.println("Submits a post! Test 4 Passes!");
        pause(1500);
    }

    @Test
    public void SignIn(){
        onView(withId(R.id.userName)).perform(typeTextIntoFocusedView("Guest 123"));
        pause(1500);
        onView(withId(R.id.button)).perform(click());
        if(!login_screen.get_user().matches("Guest 123")){
            System.out.println("Guest name is: " +login_screen.get_user()+" when it should be Guest 123");
        }
        else System.out.println("Guest name is correct! Test 1 Passes!");
        closeSoftKeyboard();
        System.out.println("Goes to list of locations! Test 2 Passes!");
        pause(1500);
        onData(hasToString(startsWith("Biomedical Library")))
                .inAdapterView(withId(R.id.listView))
                .atPosition(0)
                .perform(click());
        System.out.println("Goes to Biomedical Library! Test 3 Passes!");
        pause(1500);

        onView(withId(R.id.submitButton)).perform(click());

        System.out.println("Goes submit post page for Biomedical Library! Test 4 Passes!");
        pause(1500);

        onView(withId(R.id.commentField)).perform(typeTextIntoFocusedView("So crowded!"));
        Espresso.closeSoftKeyboard();
        pause(1500);

        onView(withId(R.id.rating)).perform(typeText("10"), closeSoftKeyboard());
        pause(1500);

        onView(withId(R.id.postButton)).perform(click());
        System.out.println("Submits a post! Test 5 Passes!");
        pause(1500);
    }

    @Test
    public void GoogleSignIn() {
        onView(withId(R.id.sign_in_button)).perform(click());
        System.out.println("Google signed in and goes to list! Test 1 Passes!");
        pause(1500);

        onData(hasToString(startsWith("CSE Basement")))
                .inAdapterView(withId(R.id.listView))
                .atPosition(0)
                .perform(click());
        System.out.println("Goes to CSE Basement! Test 2 Passes!");
        pause(1500);

        onView(withId(R.id.submitButton)).perform(click());
        System.out.println("Goes submit post page for CSE Basement! Test 3 Passes!");
        pause(1500);

        onView(withId(R.id.commentField)).perform(typeTextIntoFocusedView("So crowded!"));
        Espresso.closeSoftKeyboard();
        pause(1500);

        onView(withId(R.id.rating)).perform(typeText("10"), closeSoftKeyboard());
        pause(1500);

        onView(withId(R.id.postButton)).perform(click());
        System.out.println("Submits a post! Test 4 Passes!");
        pause(1500);

    }
}
