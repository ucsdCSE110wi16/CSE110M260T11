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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.support.test.rule.ActivityTestRule;

import com.firebase.client.Firebase;

@RunWith(AndroidJUnit4.class)
public class UpvoteTester {
    private String voteCount;

    @Rule
    public ActivityTestRule<view_page> mActivityRule = new ActivityTestRule<>(
            view_page.class);

    @Before
    public void initVoteCount(){
        //Firebase.setAndroidContext(view_page);
        voteCount = "1";
    }

    @Test
    public void clickUpArrowIncrementVoteCount(){
        onView(withId(R.id.upArrow)).perform(click());
        onView(withId(R.id.post_info)).check(matches(withText("votes: " + voteCount)));
    }


}
