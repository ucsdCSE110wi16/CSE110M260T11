package team11.crowded;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Map;

public class sort_posts implements Comparator<Map<String, String>> {

    private final SimpleDateFormat format;

    sort_posts(SimpleDateFormat f)
    {
        format = f;
    }

    public int compare(Map<String, String> a, Map<String, String> b)
    {
        try
        {
            long time_a = format.parse(a.get("time")).getTime();
            long time_b = format.parse(b.get("time")).getTime();

            int vote_a = Integer.parseInt(a.get("votes"));
            int vote_b = Integer.parseInt(b.get("votes"));

            if (vote_a < 0) return 1;
            if (vote_b < 0) return -1;

            return (time_b - time_a) >= 0 ? 1 : -1;
        }
        catch (Exception e)
        {
            return 0;
        }
    }
}
