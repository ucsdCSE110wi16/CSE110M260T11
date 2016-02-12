package team11.crowded;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class login_screen extends AppCompatActivity
{
    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setTitle("Sign in with Google Account");

        EditText username = new EditText(this);
        EditText password = new EditText(this);
        Button login = new Button(this);

        login.setText("sign in");
        login.setClickable(true);

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // todo: connect to google account for secure login
                startActivity(new Intent(login_screen.this, view_locations.class));
            }
        });

        LinearLayout layout = new LinearLayout(this);

        layout.setBackgroundColor(Color.WHITE);
        layout.addView(username);
        layout.addView(password);
        layout.addView(login);

        setContentView(layout);
    }
}