package team11.crowded;

import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class location_database {

    // private static final ArrayList<String> locations = new ArrayList<>();
    // private static final Map<String, Map<String, String>> posts = new HashMap<>();

    public static void submit_post(String name, String location, String rating, String comment) {

        Firebase db = new Firebase("https://incandescent-fire-8621.firebaseio.com/");
        Firebase posts = db.child("locations/" + location + "/posts");

        Map<String, String> post = new HashMap<>();
        post.put("name", name);
        post.put("time", "2016-02-20-13-56"); // todo: get local time
        post.put("rating", rating);
        post.put("votes", "+1");
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

                ArrayList<String> locations = new ArrayList<>();

                for (DataSnapshot location : snapshot.getChildren()) {
                    locations.add((String)location.child("name").getValue());
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