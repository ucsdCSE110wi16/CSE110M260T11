package team11.crowded;

import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class location_database {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
    private static final ArrayList<String> db_locations = new ArrayList<>();
    private static final ArrayList<String> locations = new ArrayList<>();
    private static final Map<String, ArrayList<Map<String, String>>> posts = new HashMap<>();

    private static String get_to_db_location(String location)
    {
        return db_locations.get(locations.indexOf(location));
    }

    public static ArrayList<String> get_locations()
    {
        return locations;
    }

    public static ArrayList<Map<String, String>> get_location_posts(String location)
    {
        return posts.get(location);
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

    public static void list_locations(final AppCompatActivity view, final ListView list) {

        Firebase db = new Firebase("https://incandescent-fire-8621.firebaseio.com/");
        Firebase places = db.child("locations/");

        /*places.child("geisel/name").setValue("Geisel");
        places.child("ridgewalk/name").setValue("Ridgewalk");
        places.child("biomedical_library/name").setValue("Biomedical Library");
        places.child("cse_basement/name").setValue("CSE Basement");
        places.child("main_gym/name").setValue("Main Gym");
        places.child("rimac_weight_room/name").setValue("Rimac Weight Room");
        places.child("rimac_basketball_courts/name").setValue("Rimac Basketball Courts");
        places.child("starbucks/name").setValue("Starbucks");
        places.child("price_center/name").setValue("Price Center");
        places.child("geisel_8th_floor/name").setValue("Geisel 8th Floor");
        places.child("geisel_7th_floor/name").setValue("Geisel 7th Floor");*/

        places.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                // each location
                for (DataSnapshot location : snapshot.getChildren()) {

                    String name = (String)location.child("name").getValue();
                    db_locations.add((String) location.getKey());
                    locations.add(name);

                    ArrayList<Map<String, String>> loc_posts = new ArrayList<>();

                    // each post in location
                    for (DataSnapshot post : location.child("posts/").getChildren()) {

                        loc_posts.add((HashMap<String, String>)post.getValue());
                    }

                    posts.put(name, loc_posts);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(view, android.R.layout.simple_list_item_1, locations);
                list.setAdapter(adapter);
            }

            @Override
            public void onCancelled(FirebaseError error) {
                System.out.println(error.getMessage());
            }
        });
    }
}