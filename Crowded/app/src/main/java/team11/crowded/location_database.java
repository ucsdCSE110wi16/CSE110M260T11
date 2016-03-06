package team11.crowded;

import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ImageButton;
import android.view.View;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class location_database {

//    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
    private static final SimpleDateFormat format = new SimpleDateFormat("HH-mm");
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

    private static String setTime(String time){
        Time now = new Time();
        now.setToNow();
        now.switchTimezone(now.getCurrentTimezone());
        int currHour = now.hour;
        int pHour = Integer.parseInt(time.substring(0,2));
        int pMinute = Integer.parseInt(time.substring(3,5));

        if(pHour > currHour){ //e.g. when post is pre-midnight and now its after midnight
            currHour += 24;
        }

        int hour = currHour*60;
        int minute = now.minute + hour; //total minutes
        int postHour = pHour*60;
        int postMinute = pMinute + postHour;
        hour = (minute - postMinute)/60;
        minute = (minute - postMinute)%60;
        System.out.println(time);
        System.out.println("now.hour: " + now.hour + ", now.minute: " + now.minute);
        System.out.println("hour: " + time.substring(0, 2) + ", minute: " + time.substring(3));
        System.out.println("hour: " + hour + ", minute: " + minute);
        if(hour == 1 && minute == 1) return hour + " hour and " + minute + " minute ago.";
        else if(hour == 1 && minute < 1) return hour + " hour ago.";
        else if(hour == 1 && minute > 1) return hour + " hour and " + minute + " minutes ago.";
        else if(hour > 1 && minute == 1) return hour + " hours and " + minute + " minute ago.";
        else if(hour > 1 && minute < 1) return hour + " hours ago.";
        else if(hour > 1 && minute > 1) return hour + " hours and " + minute + " minutes ago.";
        else if(hour == 0 && minute == 1) return minute + " minute ago.";
        else if(hour == 0 && minute < 1) return "just now";
        else if(hour == 0 && minute > 1) return minute + " minutes ago.";
        else return "don't know? :) ";
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

    public static void list_location_page(final AppCompatActivity view, final ListView list, final String location) {

        Firebase db = new Firebase("https://incandescent-fire-8621.firebaseio.com/");
        Firebase select = db.child("locations/" + get_to_db_location(location) + "/posts");

        select.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                ArrayList<Map<String, String>> posts = new ArrayList<>();

                for (DataSnapshot post : snapshot.getChildren()) {

                    posts.add((HashMap<String, String>) post.getValue());
                }

                ArrayList<String> post_list = new ArrayList<>();

                if (!posts.isEmpty()) {

                    Collections.sort(posts, new sort_posts(format));

                    for (Map<String, String> p : posts)
                    {
                        String post_info = "";

                        post_info += "time = " + setTime(p.get("time")) + "\n";
                        post_info += "how populated = " + p.get("rating") + "\n";
                        post_info += "votes = " + p.get("votes") + "\n";
                        post_info += "comment = " + p.get("comment") + "\n";
                        post_info += "name = " + p.get("name") + "\n";

                        post_list.add(post_info);
                    }
                }
                else {
                    post_list.add("No posts found");
                }

                //ArrayAdapter<String> adapter = new ArrayAdapter<String>(view, R.layout.location_list, R.id.post_info, post_list);
                CustomAdapter adapter = new CustomAdapter(view, R.layout.location_list, R.id.post_info, post_list);
                list.setAdapter(adapter);

                //ImageButton upArrow = (ImageButton) list.findViewById(R.id.upArrow);
                //ImageButton downArrow = (ImageButton) list.findViewById(R.id.downArrow);

                if (login_screen.get_user().equals("")) {
                    //upArrow.setClickable(false);
                    //downArrow.setClickable(false);
                }
                else {
                    /*upArrow.setClickable(true);
                    downArrow.setClickable(true);
                    upArrow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //votes++;
                        }
                    });
                    downArrow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //votes--;
                        }
                    });*/
                }
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