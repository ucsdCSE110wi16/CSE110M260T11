package team11.crowded;

import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.MutableData;
import com.firebase.client.Transaction;
import com.firebase.client.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class location_database {

     private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
    private static final ArrayList<String> db_locations = new ArrayList<>();
    private static final ArrayList<String> locations = new ArrayList<>();

    private static String get_to_db_location(String location)
    {
        return db_locations.get(locations.indexOf(location));
    }

    public static ArrayList<String> get_locations()
    {
        return locations;
    }

    public static String setTime(String time){
        Time now = new Time();
        now.setToNow();
        now.switchTimezone(now.getCurrentTimezone());
        int currHour = now.hour;
        int pHour = Integer.parseInt(time.substring(11,13));
        int pMinute = Integer.parseInt(time.substring(14, 16));

        //when year/month/day don't equal
        int currYear = now.year;
        int currMonth = now.month + 1;
        int currDay = now.monthDay;

        int pYear = Integer.parseInt(time.substring(0,4));
        int pMonth = Integer.parseInt(time.substring(5,7));
        int pDay = Integer.parseInt(time.substring(8,10));

        if(currYear!=pYear) return Integer.toString(currYear-pYear) + "year(s) ago";
        if(currMonth != pMonth) return time;
        if(currDay - pDay == 1 && pHour < currHour) return "over a day ago";
        else if(currDay - pDay >= 2) return "over " + (currDay - pDay) + " days ago";
        //less than 24 hours ago
        else if(pHour > currHour){ //e.g. when post is pre-midnight and now its after midnight
            currHour += 24;
        }

        int hour = currHour*60;
        int minute = now.minute + hour; //total minutes
        int postHour = pHour*60;
        int postMinute = pMinute + postHour;

        hour = (minute - postMinute)/60;
        minute = (minute - postMinute)%60;
        if(hour == 1 && minute == 1) return hour + " hour and " + minute + " minute ago.";
        else if(hour == 1 && minute < 1) return hour + " hour ago.";
        else if(hour == 1 && minute > 1) return hour + " hour and " + minute + " minutes ago.";
        else if(hour > 1 && minute == 1) return hour + " hours and " + minute + " minute ago.";
        else if(hour > 1 && minute < 1) return hour + " hours ago.";
        else if(hour > 1 && minute > 1) return hour + " hours and " + minute + " minutes ago.";
        else if(hour == 0 && minute == 1) return minute + " minute ago.";
        else if(hour == 0 && minute < 1) return "just now";
        else if(hour == 0 && minute > 1) return minute + " minutes ago.";
        else return time;
    }

    public static void submit_post(String name, String location, String rating, String comment) {

        Firebase db = new Firebase("https://incandescent-fire-8621.firebaseio.com/");
        Firebase posts = db.child("locations/" + get_to_db_location(location) + "/posts");

        Map<String, String> post = new HashMap<>();
        post.put("name", name);
        post.put("time", format.format(new Date()));
        post.put("rating", rating);
        post.put("votes", "+0");
        post.put("comment", comment);
        posts.push().setValue(post);
    }

    public static void submit_vote(String location, String post_id, final int vote)
    {
        Firebase db = new Firebase("https://incandescent-fire-8621.firebaseio.com/");
        Firebase post_votes = db.child("locations/" + get_to_db_location(location) + "/posts/" + post_id + "/votes");

        post_votes.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData data) {
                if (data.getValue() == null) {
                    data.setValue("+0");
                } else {
                    try {
                        int parse_vote = Integer.parseInt((String) data.getValue()) + vote;
                        String vote_string = Integer.toString(parse_vote);
                        if (parse_vote >= 0) vote_string = "+" + vote_string;
                        data.setValue(vote_string);
                    } catch (Exception e) {

                    }
                }

                return Transaction.success(data);
            }

            @Override
            public void onComplete(FirebaseError error, boolean committed, DataSnapshot data) {

            }
        });
    }

    public static void list_location_page(final AppCompatActivity view, final ListView list, final String location) {

        Firebase db = new Firebase("https://incandescent-fire-8621.firebaseio.com/");
        Firebase select = db.child("locations/" + get_to_db_location(location) + "/posts");
        select.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {

                ArrayList<Map<String, String>> posts = new ArrayList<>();

                for (DataSnapshot post : snapshot.getChildren()) {

                    HashMap<String, String> hash_post_data = (HashMap<String, String>) post.getValue();
                    hash_post_data.put("id", post.getKey());
                    posts.add(hash_post_data);
                }

                ArrayList<String> post_list = new ArrayList<>();

                if (!posts.isEmpty()) {

                    Collections.sort(posts, new sort_posts(format));

                    for (Map<String, String> p : posts) {
                        String post_info = "";

                        try {
                            post_info += p.get("id") + "\n";
                            post_info += setTime(p.get("time")) + "\n";
                            post_info += "How Populated: " + p.get("rating") + "\n";
                            post_info += "Votes: " + p.get("votes") + "\n";
                            post_info += "Comment: " + p.get("comment") + "\n";
                            post_info += "By: " + p.get("name") + "\n";
                        } catch (Exception e) {
                            post_info = "error\nerror loading post.\n";
                        }

                        post_list.add(post_info);
                    }
                } else {
                    post_list.add("No posts found");
                }

                CustomAdapter adapter = new CustomAdapter(view, R.layout.location_list, R.id.post_info, post_list);
                list.setAdapter(adapter);

                view.setTitle("Viewing " + location);
            }

            @Override
            public void onCancelled(FirebaseError error) {
                System.out.println(error.getMessage());
            }
        });
    }

    public static void list_locations(final AppCompatActivity view, final ListView list) {

        Firebase db = new Firebase("https://incandescent-fire-8621.firebaseio.com/");
        Firebase places = db.child("locations/");

        places.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                locations.clear();
                db_locations.clear();

                // each location
                for (DataSnapshot location : snapshot.getChildren()) {

                    String name = (String) location.child("name").getValue();
                    db_locations.add((String) location.getKey());
                    locations.add(name);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(view, android.R.layout.simple_list_item_1, locations);
                list.setAdapter(adapter);

                view.setTitle("Choose a Location");
            }

            @Override
            public void onCancelled(FirebaseError error) {
                System.out.println(error.getMessage());
            }
        });
    }
}