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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class view_page extends AppCompatActivity
{
    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setTitle("Status of This Location");

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        RelativeLayout layout = new RelativeLayout(this);

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
        Intent myIntent = new Intent(getApplicationContext(), login_screen.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
