package team11.crowded;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.EditText;
import android.view.View;
import android.graphics.Color;

public class add_comment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setTitle("Submit a post for " + view_page.get_location());

        LinearLayout LL = new LinearLayout(this);
        LL.setBackgroundColor(Color.WHITE);
        LL.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams LLParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        LL.setLayoutParams(LLParams);

        final EditText rating = new EditText(this);
        rating.setHint("How populated out of 10");
        rating.setFocusable(true);
        rating.setSingleLine(true);
        rating.setMaxLines(1);
        rating.setInputType(InputType.TYPE_CLASS_NUMBER);
        LinearLayout.LayoutParams rating_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rating_params.gravity = Gravity.CENTER_VERTICAL;
        rating.setLayoutParams(rating_params);

        final EditText comment = new EditText(this);
        comment.setHint("Enter a comment");
        comment.setFocusable(true);
        comment.setSingleLine(true);
        comment.setMaxLines(1);
        LinearLayout.LayoutParams comment_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        comment_params.gravity = Gravity.CENTER_VERTICAL;
        comment.setLayoutParams(comment_params);

        Button submit = new Button(this);
        submit.setText("Submit");
        submit.setClickable(false);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pop = Integer.parseInt(rating.getText().toString());
                if( pop > 10 ) {
                    rating.setText("10");
                }
                location_database.submit_post(login_screen.get_name(), view_page.get_location(), rating.getText().toString(), comment.getText().toString());

                startActivity(new Intent(add_comment.this, view_page.class));

                rating.setText("");
                comment.setText("");
            }
        });
        LinearLayout.LayoutParams submit_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        submit_params.gravity = Gravity.BOTTOM;
        submit.setLayoutParams(submit_params);

        LL.addView(rating);
        LL.addView(comment);
        LL.addView(submit);

        setContentView(LL);
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        startActivityForResult(new Intent(getApplicationContext(), view_page.class), 0);
        return true;
    }
}
