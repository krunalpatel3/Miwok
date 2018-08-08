package krunal.com.example.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by acer on 9/10/2017.
 */

public class WordAdapter  extends ArrayAdapter<Words> {

    private int mcolorid;

    public WordAdapter(Activity context, ArrayList<Words> Words, int color) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.\

        super(context, 0, Words);
        mcolorid = color;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }


        Words currentAndroid = getItem(position);


        TextView nameTextView = listItemView.findViewById(R.id.miwok);

        nameTextView.setText(currentAndroid.getMiwok());


        TextView numberTextView = listItemView.findViewById(R.id.eng);

        numberTextView.setText(currentAndroid.geteng());

        ImageView imgeview = listItemView.findViewById(R.id.img);

        if (currentAndroid.hasImage()) {
            imgeview.setImageResource(currentAndroid.getimage());
            imgeview.setVisibility(View.VISIBLE);
        } else {
            imgeview.setVisibility(View.GONE);
        }

        View textContainer = listItemView.findViewById(R.id.textcontner);

        int color = ContextCompat.getColor(getContext(), mcolorid);

        textContainer.setBackgroundColor(color);
        return listItemView;
    }
}