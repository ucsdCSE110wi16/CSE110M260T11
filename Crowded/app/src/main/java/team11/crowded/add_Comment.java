package team11.crowded;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.EditText;
import android.view.View;
import android.graphics.Color;

public class add_Comment extends AppCompatActivity {

    private static final String RATING_EMPTY = "1";
    private static final String RATING_SOME  = "2";
    private static final String RATING_FULL  = "3";

    String comment = "";
    private static String location = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle( "Add a comment!" );

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        RelativeLayout layout = new RelativeLayout(this);
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        param.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        param.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        Button submit = new Button(this);
        final EditText commentBox = new EditText(this);

        commentBox.setHint( "Enter comment here" );
        commentBox.setEnabled(true);
        submit.setText("Submit");
        submit.setClickable(true);
        layout.setBackgroundColor(Color.WHITE);

        layout.addView( submit, param );
        layout.addView( commentBox );
        setContentView( layout );

        submit.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick( View view ) {
                comment = commentBox.getText().toString();
                // todo: change this example submission to take user input
                location_database.submit_post("user example", location, RATING_EMPTY, comment);
            }
        });
    }

    public static void setPosition(String loc) {
        location = loc;
    }

    public String getComment() {
        return comment;
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Intent myIntent = new Intent(getApplicationContext(), view_page.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
