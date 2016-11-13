package com.movie.som.popularmoviestage2;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.movie.som.popularmoviestage2.databaseHelper.DatabaseHandler;
import com.movie.som.popularmoviestage2.databaseHelper.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieDetail extends AppCompatActivity {

    private String data;
    public  String movieid;
    private String title;
    private String overview;
    private String vote;
    private String releasedate;
    private String poster;
    private String backdrop_image;

   private int moviePresent=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Stetho.initializeWithDefaults(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container2, new MovieDetailFragment())
                    .commit();
        }

        if (getIntent() != null) {
            Bundle args = new Bundle();
            String data = getIntent().getStringExtra("title");
            args.putString("title", data);
            data=getIntent().getStringExtra("overview");
            args.putString("overview", data);
            data=getIntent().getStringExtra("vote_average");
            args.putString("vote_average", data);
            data=getIntent().getStringExtra("release_date");
            args.putString("release_date", data);
            data=getIntent().getStringExtra("url");
            args.putString("url", data);
            data=getIntent().getStringExtra("id");
            args.putString("id", data);
            data=getIntent().getStringExtra("url2");
            args.putString("url2", data);


            MovieDetailFragment detailFragment = new MovieDetailFragment();
            detailFragment.setArguments(args);
            getSupportFragmentManager().beginTransaction().replace(R.id.container2, detailFragment).commit();

        }


    }



    public void video(View view){

        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=cxLG2wtE7TM")));



    }

}
