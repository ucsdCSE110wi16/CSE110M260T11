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

        ListView list = new ListView(this);
        list.setBackgroundColor(Color.WHITE);

        location_database.list_locations(this, list);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                startActivity(new Intent(view_locations.this, view_page.class));
            }
        });

        setContentView(list);
    }
}