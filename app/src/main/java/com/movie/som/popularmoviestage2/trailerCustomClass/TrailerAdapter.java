package com.movie.som.popularmoviestage2.trailerCustomClass;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.movie.som.popularmoviestage2.R;

import java.util.List;

public class TrailerAdapter extends ArrayAdapter<MovieTrailer> {
    private static final String LOG_TAG = TrailerAdapter.class.getSimpleName();


    public TrailerAdapter(Activity context, List<MovieTrailer> movieTrailers) {

        super(context, 0, movieTrailers);
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        MovieTrailer movieTrailer = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_trailer_custom_layout, parent, false);
        }



        TextView versionNameView = (TextView) convertView.findViewById(R.id.list_item_Trailer_name);
        versionNameView.setText(movieTrailer.trailerName);


        return convertView;
    }
}
