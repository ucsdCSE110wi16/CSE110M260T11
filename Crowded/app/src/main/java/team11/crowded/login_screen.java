package team11.crowded;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class login_screen extends AppCompatActivity
{
    private GoogleApiClient mGoogleApiClient;


    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setTitle("Sign in with Google Account");

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        //mGoogleApiClient = new GoogleApiClient.Builder(this)
         //       .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
           //     .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
             //   .build();


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
                //startActivity(new Intent(login_screen.this, view_locations.class));
                startActivity(new Intent(login_screen.this, view_page.class));
            }
        });

        LinearLayout layout = new LinearLayout(this);


        layout.setBackgroundColor(Color.WHITE);
        layout.addView(username);
        layout.addView(password);
        layout.addView(login);

        setContentView(layout);

/*
        //sign in button
        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setScopes(gso.getScopeArray());

        //When signin button is clicked
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);

*/


    }
}