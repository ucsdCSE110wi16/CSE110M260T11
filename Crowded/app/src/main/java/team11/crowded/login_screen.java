package team11.crowded;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;
import android.util.Log;
import android.widget.RelativeLayout;

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

    //private ProgressDialog progress;

    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Firebase.setAndroidContext(this);

       // setTitle("Sign in with Google Account");

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


        final EditText username = (EditText)findViewById(R.id.userName);
        username.setMaxLines(1);
        username.setFocusable(true);


        final EditText password = (EditText)findViewById(R.id.password);
        password.setFocusable(true);
        password.setSingleLine(true);
        password.setMaxLines(1);


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
        //signOut.setText("Sign out of Google");
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

        /*
        LinearLayout LL = new LinearLayout(this);
        LL.setBackgroundColor(Color.WHITE);
        LL.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams LLParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        LL.setLayoutParams(LLParams);

        ImageView logo = new ImageView(this);
        logo.setImageResource(R.drawable.logo);
        logo.setScaleType(ImageView.ScaleType.FIT_CENTER);
        FrameLayout.LayoutParams logoParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
        logoParams.gravity = Gravity.CENTER_VERTICAL;
        logo.setLayoutParams(logoParams);


        final EditText username = new EditText(this);
        username.setHint("Enter user name");
        username.setFocusable(true);
        username.setSingleLine(true);
        username.setMaxLines(1);
        LinearLayout.LayoutParams usernameParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        usernameParams.gravity = Gravity.CENTER_VERTICAL;
        username.setLayoutParams(usernameParams);

         final EditText password = new EditText(this);
         password.setHint("Enter password");
         password.setFocusable(true);
         password.setSingleLine(true);
         password.setMaxLines(1);
         password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
         LinearLayout.LayoutParams passwordParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
         passwordParams.gravity = Gravity.CENTER_VERTICAL;
         password.setLayoutParams(passwordParams);



        Button login = new Button(this);
        login.setText("Sign in as guest");
        login.setClickable(false);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current_user = username.getText().toString();
                if( username.getText().toString().equals("") ) {
                    user_name = "Guest User";
                }
                else {
                    user_name = username.getText().toString();
                }
                startActivity(new Intent(login_screen.this, view_locations.class));
            }
        });


        LinearLayout.LayoutParams loginParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        loginParams.gravity = Gravity.BOTTOM;
        login.setLayoutParams(loginParams);


        SignInButton login_google = new SignInButton(this);
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
        //LinearLayout.LayoutParams googleParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        //googleParams.gravity = Gravity.BOTTOM;
        //login_google.setLayoutParams(googleParams);

        //Button signOut = new Button(this);
        Button signOut = (Button) findViewById(R.id.signOut);
        signOut.setClickable(true);
        signOut.setText("Sign out of Google");
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });
     //   LinearLayout.LayoutParams signOutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
     //   signOutParams.gravity = Gravity.BOTTOM;
     //   signOut.setLayoutParams(signOutParams);

        /*
        LL.addView(logo);
        LL.addView(username);
        LL.addView(password);
        LL.addView(login_google);
        LL.addView(login);
        LL.addView(signOut);

        setContentView(LL);
        */
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
