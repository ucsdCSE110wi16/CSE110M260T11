package team11.crowded;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.view.View;
import android.widget.ListView;

public class view_locations extends AppCompatActivity
{
    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setTitle("Loading Locations. Please wait");

        setContentView(R.layout.locationview);
        ListView list = (ListView) findViewById(R.id.listView);

        location_database.list_locations(this, list);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                view_page.set_location(adapter.getItemAtPosition(position).toString());
                startActivity(new Intent(view_locations.this, view_page.class));
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        startActivityForResult(new Intent(getApplicationContext(), login_screen.class), 0);
        return true;
    }
}