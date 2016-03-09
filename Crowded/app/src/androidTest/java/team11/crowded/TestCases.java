package team11.crowded;

import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class TestCases {

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

        String time = "1995-01-01-01-01";
        location_database.setTime(time).matches("more than a year");
    }
}