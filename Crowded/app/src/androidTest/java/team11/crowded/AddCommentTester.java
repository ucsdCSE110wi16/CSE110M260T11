package team11.crowded;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.widget.ListView;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


import android.support.test.rule.ActivityTestRule;

@RunWith(AndroidJUnit4.class)
public class AddCommentTester {
    private String to_comment;
    private String to_rating;


    @Rule
    public ActivityTestRule<add_comment> mActivityRule = new ActivityTestRule<>(
            add_comment.class);
    @Before
    public void initVars(){
        to_comment = "There's a coding competition going on in the labs today, so the labs are packed!";
        to_rating = "10";
    }

    @Test
    public void submitComment(){
        // Type text and then press the button.
        /*onView(withId(R.id.(add_comment.rating).getId())).check(matches(withText("10")));
        onView(withId(findByView(findViewById(comment.getId()))))
                .perform(typeText(comment), closeSoftKeyboard());

        onView(withId(R.findByView(findViewById(submit.getId())))).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.textToBeChanged))
                .check(matches(withText(mStringToBetyped)));*/
    }
}
