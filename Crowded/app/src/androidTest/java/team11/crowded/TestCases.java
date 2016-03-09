package team11.crowded;

import android.support.test.runner.AndroidJUnit4;
import android.text.format.Time;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static junit.framework.Assert.assertEquals;

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

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        cal.add(Calendar.SECOND, -1);
        assertEquals("just now", location_database.setTime(format.format(cal.getTime())));

        cal.add(Calendar.SECOND, -60);
        assertEquals("1 minute ago.", location_database.setTime(format.format(cal.getTime())));

        cal.add(Calendar.SECOND, +61);
        cal.add(Calendar.HOUR, -1);
        assertEquals("1 hour ago.", location_database.setTime(format.format(cal.getTime())));

        cal.add(Calendar.SECOND, -121);
        cal.add(Calendar.HOUR, -1);
        assertEquals("2 hours and 2 minutes ago.", location_database.setTime(format.format(cal.getTime())));

        cal.add(Calendar.YEAR, -1);
        assertEquals("1 year(s) ago.", location_database.setTime(format.format(cal.getTime())));
    }
}