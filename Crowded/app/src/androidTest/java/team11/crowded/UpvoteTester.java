package team11.crowded;

import android.support.test.runner.AndroidJUnitRunner;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.junit.Rule;





/**
 * Created by Ari on 3/6/2016.
 */

@RunWith(AndroidJUnit4.class)
public class UpvoteTester {
    int voteCount;

    @Rule public final ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule(MainActivity.class);

    @Before
    public void initvoteCount() {
        voteCount = 0;
    }

    @Test
    public void clickButtonIncrementsVoteCount(){
        onView(withId(R.id.upArrow)).performf(click());
    }
}
