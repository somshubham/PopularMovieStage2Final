package com.movie.som.popularmoviestage2.reviewCustomClass;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.movie.som.popularmoviestage2.R;

import java.util.List;

public class ReviewAdapter extends ArrayAdapter<MovieReview> {
    private static final String LOG_TAG = ReviewAdapter.class.getSimpleName();


    public ReviewAdapter(Activity context, List<MovieReview> movieTrailers) {

        super(context, 0, movieTrailers);
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        MovieReview movieReview = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_review_custom_layout, parent, false);
        }



        TextView author = (TextView) convertView.findViewById(R.id.list_item_Reviewer_name);
        author.setText(movieReview.author);
        TextView review = (TextView) convertView.findViewById(R.id.textViewReview);
        review.setText(movieReview.content);



        return convertView;
    }
}
