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

import com.firebase.client.Firebase;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

public class login_screen extends AppCompatActivity
{
    private GoogleApiClient mGoogleApiClient;

    private static String current_user = "";

    public static String get_user()
    {
        return current_user;
    }

    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Firebase.setAndroidContext(this);

        setTitle("Sign in with Google Account");

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        //mGoogleApiClient = new GoogleApiClient.Builder(this)
         //       .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
           //     .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
             //   .build();

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
        login.setText("Sign in");
        login.setClickable(false);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current_user = username.getText().toString();
                startActivity(new Intent(login_screen.this, view_locations.class));
            }
        });
        LinearLayout.LayoutParams loginParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        loginParams.gravity = Gravity.BOTTOM;
        login.setLayoutParams(loginParams);

        LL.addView(logo);
        LL.addView(username);
        LL.addView(password);
        LL.addView(login);

        setContentView(LL);
    }
}