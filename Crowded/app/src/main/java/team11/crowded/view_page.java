package team11.crowded;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageButton;

public class view_page extends AppCompatActivity
{
    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setTitle("Status of This Location");

        ImageButton goBack = new ImageButton(this);
        goBack.setImageResource(R.drawable.back_button);
    }
}
