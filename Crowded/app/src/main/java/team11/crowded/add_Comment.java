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

    String comment = "";
    private static int position = 0;

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
                commentBox.setText(String.valueOf(position));
            }
        });
    }

    public static void setPosition(int location) {
        position = location;
    }

    public String getComment() {
        return comment;
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Intent myIntent = new Intent(getApplicationContext(), login_screen.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
