package team11.crowded;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.graphics.Color;

public class view_locations extends AppCompatActivity
{
    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setTitle("Choose a Location");

        String[] locations = location_database.get_locations();

        ListView list_locations = new ListView(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, locations);
        list_locations.setAdapter(adapter);
        list_locations.setBackgroundColor(Color.WHITE);

       list_locations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
               // todo: connect to google account for secure login
               startActivity(new Intent(view_locations.this, add_Comment.class));
               add_Comment.setPosition( position );
            }
        });

        // todo: on click event for items in location list transfers to view_page activity
        /*list_locations.setOnItemClickListener(new AdapterView.onItemClickListener()
        {
            @Override public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent appInfo = new Intent(YourActivity.this, ApkInfoActivity.class);
                startActivity(appInfo);
            }
        });*/

        setContentView(list_locations);
    }
}