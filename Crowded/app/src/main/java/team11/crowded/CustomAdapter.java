package team11.crowded;

import android.opengl.Visibility;
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

    Context activity;
    ArrayList<String> list;

    public CustomAdapter(Context activity, int resource, int textViewResourceId, ArrayList<String> list) {
        super( activity, resource, textViewResourceId, list);
        this.activity = activity;
        this.list = list;
    }

    private static void changeVoteInPost(TextView post, int vote) {
        String str = post.getText().toString();
        String updated;

        final String to_find = "votes = ";

        int start = str.indexOf(to_find);
        int end = str.indexOf("\n", start);

        String str_vote = str.substring(start + to_find.length(), end);

        int parse_vote = Integer.parseInt(str_vote) + vote;

        String vote_string = Integer.toString(parse_vote);

        if (parse_vote >= 0) vote_string = "+" + vote_string;

        updated = str.substring(0, start + to_find.length()) + vote_string + str.substring(end, str.length());

        post.setText(updated);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null ) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.location_list,parent, false);
        }

        final TextView post_info = (TextView)convertView.findViewById(R.id.post_info);
        final ImageButton up_Arrow = (ImageButton) convertView.findViewById(R.id.upArrow);
        final ImageButton down_Arrow = (ImageButton) convertView.findViewById(R.id.downArrow);

        if (list.get(position).indexOf("\n") < 0)
        {
            post_info.setText("\n" + list.get(position));
            up_Arrow.setVisibility(View.GONE);
            down_Arrow.setVisibility(View.GONE);
            return convertView;
        }

        String post_data = list.get(position);
        String post_id = post_data.substring(0, post_data.indexOf("\n"));

        post_info.setText(post_data.substring(post_data.indexOf("\n"), post_data.length()));

        up_Arrow.setTag(post_id);
        down_Arrow.setTag(post_id);

        if (login_screen.get_user().equals("")) {
            up_Arrow.setVisibility(View.GONE);
            down_Arrow.setVisibility(View.GONE);
        }
        else
        {
            up_Arrow.setClickable(true);
            down_Arrow.setClickable(true);
            up_Arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (up_Arrow.getVisibility() == View.VISIBLE) {

                        String click_id = (String) view.getTag();

                        location_database.submit_vote(view_page.get_location(), click_id, +1);
                        changeVoteInPost(post_info, +1);

                        up_Arrow.setVisibility(View.INVISIBLE);
                        down_Arrow.setVisibility(View.VISIBLE);
                    }
                }
            });
            down_Arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (down_Arrow.getVisibility() == View.VISIBLE) {

                        String click_id = (String) view.getTag();

                        location_database.submit_vote(view_page.get_location(), click_id, -1);
                        changeVoteInPost(post_info, -1);

                        up_Arrow.setVisibility(View.VISIBLE);
                        down_Arrow.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }

        return convertView;
    }
}