package team11.crowded;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.graphics.Color;

public class view_locations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_view_locations);

        RelativeLayout rel = new RelativeLayout(this);

        Button but = new Button(this);

        but.setClickable(true);
        but.setText("click me");

        rel.setBackgroundColor(Color.BLUE);

        rel.addView(but);

        // setContentView(rel);

        ListView list_locations = new ListView(this);

        String[] locations = {"Geisel", "RIMAC", "Starbucks"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, locations);
        list_locations.setAdapter(adapter);

        list_locations.setBackgroundColor(Color.RED);

        setContentView(list_locations);
    }
}
