package team11.crowded;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.ImageButton;
import android.view.LayoutInflater;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;



public class CustomAdapter extends ArrayAdapter{

    ArrayList<String> list;

    public CustomAdapter(Context activity, int resource, int textViewResourceId, ArrayList<String> list) {
        super( activity, resource, textViewResourceId, list);
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null ) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.location_list,parent, false);
        }

        TextView post_info = (TextView) convertView.findViewById(R.id.post_info);
        post_info.setText(list.get(position));

        ImageButton up_Arrow = (ImageButton) convertView.findViewById(R.id.upArrow);
        ImageButton down_Arrow = (ImageButton) convertView.findViewById(R.id.downArrow);

        if( post_info.getText().toString().equals("No posts found")) {
            up_Arrow.setVisibility(View.GONE);
            down_Arrow.setVisibility(View.GONE);
        }

        if (login_screen.get_user().equals("")) {
            up_Arrow.setClickable(false);
            down_Arrow.setClickable(false);
        }
        else {
            up_Arrow.setClickable(true);
            down_Arrow.setClickable(true);
            up_Arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //location_database.setVote( votes, 1 );
                    System.out.println("test");
                }
            });
            down_Arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }
        return convertView;
    }
}
