package team11.crowded;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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