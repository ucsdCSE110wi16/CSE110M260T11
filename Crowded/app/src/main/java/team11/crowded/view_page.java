package team11.crowded;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

public class view_page extends AppCompatActivity
{
    private static String current_location = "";

    public static void set_location(String location)
    {
        current_location = location;
    }

    public static String get_location()
    {
        return current_location;
    }

    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setTitle("Loading Posts. Please wait");

        LinearLayout LL = new LinearLayout(this);
        LL.setBackgroundColor(Color.WHITE);
        LL.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams LLParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        LL.setLayoutParams(LLParams);

        Button submission = new Button(this);
        submission.setId(R.id.submitButton);
        submission.setText("Submit a Post");
        submission.setClickable(false);
        submission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view_page.this, add_comment.class));
            }
        });
        LinearLayout.LayoutParams loginParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        loginParams.gravity = Gravity.BOTTOM;
        submission.setLayoutParams(loginParams);

        ListView list = new ListView(this);
        list.setBackgroundColor(Color.WHITE);
        location_database.list_location_page(this, list, current_location);
        LinearLayout.LayoutParams listParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        listParams.gravity = Gravity.TOP;
        list.setLayoutParams(listParams);

        LL.addView(list);
        LL.addView(submission);

        setContentView(LL);

    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        startActivityForResult(new Intent(getApplicationContext(), view_locations.class), 0);
        return true;
    }
}
