package team11.crowded;

import android.support.test.runner.AndroidJUnit4;
import android.text.format.Time;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.Date;

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
}