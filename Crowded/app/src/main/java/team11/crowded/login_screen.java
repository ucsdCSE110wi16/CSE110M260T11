package team11.crowded;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

import com.firebase.client.Firebase;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.GoogleApiClient;

public class login_screen extends AppCompatActivity
{
    private final int RC_SIGN_IN = 9001;

    private static String current_user = "";

    private static String user_name = "";

    public static String get_name() { return user_name; }

    public static String get_user()
    {
        return current_user;
    }

    private GoogleApiClient mGoogleApiClient;

    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Firebase.setAndroidContext(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult connectionResult) {
                        Log.d("SignInActivity", "onConnectionFail:" + connectionResult);
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        setContentView(R.layout.login_screen);

        setTitle("Welcome to Krowded!");


        final EditText username = (EditText)findViewById(R.id.userName);
        username.setFocusable(true);
        username.setMaxLines(1);
        username.setFocusable(true);

        Button login = (Button)findViewById(R.id.button);
        login.setClickable(false);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current_user = username.getText().toString();
                if (username.getText().toString().equals("")) {
                    user_name = "Guest User";
                } else {
                    user_name = username.getText().toString();
                }
                startActivity(new Intent(login_screen.this, view_locations.class));
            }
        });

        SignInButton login_google = (SignInButton) findViewById(R.id.sign_in_button);
        login_google.setClickable(true);
        login_google.setSize(SignInButton.SIZE_STANDARD);
        login_google.setScopes(gso.getScopeArray());
        login_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });



        Button signOut = (Button)findViewById(R.id.signOut);
        signOut.setClickable(true);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();

                AlertDialog.Builder signOutBox = new AlertDialog.Builder(login_screen.this);
                signOutBox.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                AlertDialog signBox= signOutBox.create();
                signBox.setMessage("Signed out of Google!");
                signBox.show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    public void handleSignInResult(GoogleSignInResult result) {
        Log.d("SignInActivity", "handleSignInResult:" + result.isSuccess());
        if(result.isSuccess())
        {
            GoogleSignInAccount account = result.getSignInAccount();
            current_user = account.getEmail();
            user_name = account.getDisplayName();
            startActivity(new Intent(login_screen.this, view_locations.class));
        }
    }

    public void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                    }
                });
    }
}
