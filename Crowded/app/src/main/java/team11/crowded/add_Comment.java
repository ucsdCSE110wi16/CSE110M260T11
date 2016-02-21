package team11.crowded;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class add_Comment extends AppCompatActivity {

    String comment;
    EditText commentBox = new EditText(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RelativeLayout layout = new RelativeLayout(this);
        Button submit = new Button(this);

        submit.setText("Submit");
        submit.setClickable(true);
        layout.setBackgroundColor(Color.BLUE);
        layout.addView( submit );

        layout.addView( commentBox );

        setContentView( layout );

        submit.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick( View view ) {
                comment = commentBox.getText().toString();
            }
        });

    }
}
