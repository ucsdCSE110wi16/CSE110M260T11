package team11.crowded;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

public class add_comment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setTitle("Submit a post for " + view_page.get_location());

        setContentView(R.layout.addcommentlayout);


        final EditText rating = (EditText) findViewById(R.id.rating);
        rating.setFocusable(true);
        rating.setSingleLine(true);
        rating.setMaxLines(1);
        rating.setInputType(InputType.TYPE_CLASS_NUMBER);


        final EditText comment = (EditText) findViewById(R.id.commentField);
        rating.setFocusable(true);
        rating.setSingleLine(true);
        rating.setMaxLines(1);
        rating.setInputType(InputType.TYPE_CLASS_NUMBER);


        Button submit = (Button) findViewById(R.id.postButton);
        submit.setClickable(false);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rating.getText().toString().equals("") || comment.getText().toString().equals("")) {
                    AlertDialog.Builder needRating = new AlertDialog.Builder(add_comment.this);

                    needRating.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
                    AlertDialog errorBox = needRating.create();
                    errorBox.setMessage("Must enter a rating AND comment");
                    errorBox.show();
                } else {
                    int pop = Integer.parseInt(rating.getText().toString());
                    if (pop > 10) {
                        rating.setText("10");
                    }
                    location_database.submit_post(login_screen.get_name(), view_page.get_location(), rating.getText().toString(), comment.getText().toString());
                    startActivity(new Intent(add_comment.this, view_page.class));
                    rating.setText("");
                    comment.setText("");
                }
            }
        });
    }


    public boolean onOptionsItemSelected(MenuItem item)
    {
        startActivityForResult(new Intent(getApplicationContext(), view_page.class), 0);
        return true;
    }
}
