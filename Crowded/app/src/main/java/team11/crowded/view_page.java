package team11.crowded;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class view_page extends AppCompatActivity
{
    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setTitle("Status of This Location");

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        RelativeLayout layout = new RelativeLayout(this);

        // todo: change this example location to currently viewed location
        ArrayList<Map<String, String>> posts = location_database.get_location_posts("Biomedical Library");

        // todo: make a swipable list for every post

        // example of getting a value from a posts
        if (!posts.isEmpty()) {
            EditText temporary_show_post = new EditText(this);
            temporary_show_post.setHint("user's comment: " + posts.get(0).get("comment"));
            layout.addView(temporary_show_post);
        }

        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        param.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        param.addRule(RelativeLayout.CENTER_HORIZONTAL);

        Button addComment = new Button(this);
        addComment.setText("Add Comment");
        addComment.setClickable(true);

        layout.addView(addComment, param);

        layout.setBackgroundColor(Color.WHITE);
        setContentView(layout);

        addComment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(view_page.this, add_Comment.class));
            }
        });

    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Intent myIntent = new Intent(getApplicationContext(), view_locations.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
