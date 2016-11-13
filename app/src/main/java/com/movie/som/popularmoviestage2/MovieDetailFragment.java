package com.movie.som.popularmoviestage2;


import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;
import com.movie.som.popularmoviestage2.databaseHelper.DatabaseHandler;
import com.movie.som.popularmoviestage2.databaseHelper.Movie;
import com.movie.som.popularmoviestage2.listviewHelper.Helper;
import com.movie.som.popularmoviestage2.reviewCustomClass.FetchMovieReviewData;
import com.movie.som.popularmoviestage2.reviewCustomClass.MovieReview;
import com.movie.som.popularmoviestage2.reviewCustomClass.ReviewAdapter;
import com.movie.som.popularmoviestage2.trailerCustomClass.FetchMovieTrailerData;
import com.movie.som.popularmoviestage2.trailerCustomClass.MovieTrailer;
import com.movie.som.popularmoviestage2.trailerCustomClass.TrailerAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class MovieDetailFragment extends Fragment {

public static TrailerAdapter adapter;
    public static ReviewAdapter adapterReview;
    private String data;
    public  String movieid;
    private String title;
    private String overview;
    private String vote;
    private String releasedate;
    private String poster;
    private String backdrop_image;

   public static   String [] urls;
    public static   String [] urls2;
   public static ListView listView;
    public static ListView listView2;
    String[] trailername;


    public static   ArrayList<MovieTrailer> trailersCount = new ArrayList<MovieTrailer>();
    public static   ArrayList<MovieReview> reviewCount = new ArrayList<MovieReview>();
    private int moviePresent=0;
    CollapsingToolbarLayout collapsingToolbarLayout;
    private DatabaseHandler db ;
    Toolbar toolbar;
    String movieIdfetched;

    public MovieDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.content_movie_detail, container, false);
        collapsingToolbarLayout = (CollapsingToolbarLayout)rootView.findViewById(R.id.collapsing_toolbar);


        if (getArguments() != null) {
            String text = getArguments().getString("ARGUMENTS");
            TextView t2 = (TextView) rootView.findViewById(R.id.overview);
            t2.setText(text);
            data = getArguments().getString("title");
            collapsingToolbarLayout.setTitle(data);
            title = data;
            data = getArguments().getString("overview");
            TextView t = (TextView) rootView.findViewById(R.id.overview);
            t.setText(data);
            overview = data;

            data = getArguments().getString("vote_average");
            t = (TextView) rootView.findViewById(R.id.User_Rating);
            t.setText(data);
            vote = data;

            data = getArguments().getString("release_date");
            t = (TextView) rootView.findViewById(R.id.Release_Date);
            t.setText(data);

            releasedate = data;
            data = getArguments().getString("url");
            ImageView imageView = (ImageView) rootView.findViewById(R.id.movie_poster);
            Picasso.with(getActivity()).load(data).into(imageView);
            poster = data;
            //my movie id ......   to fetch the data from the movie db .....
            data = getArguments().getString("id");
            movieIdfetched = data;
            movieid = data;
            TextView t1 = (TextView) rootView.findViewById(R.id.movieid);
            t1.setText(data);

            data = getArguments().getString("url2");
            backdrop_image = data;


            ImageView imageView2 = (ImageView) rootView.findViewById(R.id.backdrop_image_view);
            Log.v("backdroup_image", "" + data);
            Picasso.with(getActivity()).load(data).into(imageView2);

        }else {
            Intent intent = getActivity().getIntent();
            data = intent.getStringExtra("title");
            collapsingToolbarLayout.setTitle(data);
            title = data;
            data = intent.getStringExtra("overview");
            TextView t = (TextView) rootView.findViewById(R.id.overview);
            t.setText(data);
            overview = data;

            data = intent.getStringExtra("vote_average");
            t = (TextView) rootView.findViewById(R.id.User_Rating);
            t.setText(data);
            vote = data;

            data = intent.getStringExtra("release_date");
            t = (TextView) rootView.findViewById(R.id.Release_Date);
            t.setText(data);

            releasedate = data;
            data = intent.getStringExtra("url");
            ImageView imageView = (ImageView) rootView.findViewById(R.id.movie_poster);
            Picasso.with(getActivity()).load(data).into(imageView);
            poster = data;
            //my movie id ......   to fetch the data from the movie db .....
            data = intent.getStringExtra("id");
            movieIdfetched = data;
            movieid = data;
            TextView t1 = (TextView) rootView.findViewById(R.id.movieid);
            t1.setText(data);

            data = intent.getStringExtra("url2");
            backdrop_image = data;


            ImageView imageView2 = (ImageView) rootView.findViewById(R.id.backdrop_image_view);
            Log.v("backdroup_image", "" + data);
            Picasso.with(getActivity()).load(data).into(imageView2);
        }

         db= new DatabaseHandler(getActivity());


        // making the call to the function .....
try {
    updateMovieDetail(movieIdfetched);
}catch (Exception e)
{

}

        List<Movie> movies1 = db.getAllMovies();


        for (Movie mv : movies1) {

            String log =  mv.getMovie_id();
            if(log.equals(movieid)) {
                moviePresent++;

            }


        }
        final FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.favorite_action_button);

        if(moviePresent!=0)
        {
            fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimaryPink)));
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimaryDark)));
                //fab.setBackgroundResource(R.drawable.ic_action_favorite_selected);
                //Snackbar.make(view, "Added to favorites", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Toast.makeText(getActivity(), "ok ", Toast.LENGTH_SHORT).show();

                //adding the fab movies to the database.......


                DatabaseHandler db = new DatabaseHandler(getActivity());

                int moviePresent1=0;
                List<Movie> movies1 = db.getAllMovies();


                for (Movie mv : movies1) {

                    String log =  mv.getMovie_id();
                    if(log.equals(movieid)) {
                        moviePresent1++;

                    }


                }
                Log.v("moviepresent",""+moviePresent1);
                if(moviePresent1!=0)
                {
                    db.deleteMovie(new Movie(movieid,title,overview,vote,releasedate,poster,backdrop_image));
                    Snackbar.make(view, "Removed to favorites", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
                }
                else
                {
                    db.addMovie(new Movie(movieid,title,overview,vote,releasedate,poster,backdrop_image));
                    Snackbar.make(view, "Added to favorites", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimaryPink)));
                }




















                // Inserting Contacts
                Log.d("Insert: ", "Inserting ..");





                // Movie movie=db.getMovie(new Movie(movieid,title,overview,vote,releasedate,poster,backdrop_image));

                //  db.addMovie(new Movie(movieid,title,overview,vote,releasedate,poster,backdrop_image));

                //db.updateContact(new Movie(movieid,title,overview,vote,releasedate,poster,backdrop_image));
                // db.deleteContact(new Movie(movieid,title,overview,vote,releasedate,poster,backdrop_image));
                // db.addContact(new Contact("Ravi", "9100000000"));
                //db.addContact(new Contact("Srinivas", "9199999999"));
                // db.addContact(new Contact("Tommy", "9522222222"));
                // db.addContact(new Contact("Karthik", "9533333333"));

                // Reading all contacts
                Log.d("Reading: ", "Reading all contacts..");
                List<Movie> movies = db.getAllMovies();

                for (Movie mv : movies) {

                    String log = "Id: "+mv.getId()+"\n   Movie ID: " + mv.getMovie_id()+",\n   Title : "+mv.getTitle()+"\n   overview : "+mv.getOverview()+"\n   vote average : "+mv.getVote_average()+"\n   Release Date : "+mv.getOverview()+"\n poster:"+mv.getPoster()+"\n backdrop_image :"+mv.getBackdrop_image();
                    // Writing Contacts to log
                    Log.d("Name: ", log);



                }






















            }
        });





        //  trailersCount.add(new MovieTrailer("trailer1"));
     //  reviewCount.add(new MovieReview("",""));


        ArrayList<MovieTrailer> arrayOfTrailer = trailersCount;
                 adapter=new TrailerAdapter(getActivity(),arrayOfTrailer);
        ArrayList<MovieReview> arrayOfReview =reviewCount;
                 adapterReview=new ReviewAdapter(getActivity(),arrayOfReview);
        //geting the expandable layout android library for adding the list views .......
        ExpandableHeightListView expandableListView = (ExpandableHeightListView)rootView.findViewById(R.id.expandable_listview);

         listView=(ListView)rootView.findViewById(R.id.listView);
        // listView2=(ListView)rootView.findViewById(R.id.listView2);


                    listView.setAdapter(adapter);
                    expandableListView.setAdapter(adapterReview);
        //to set the expand of the list view ........
                    expandableListView.setExpanded(true);
        //to disable the listview click
                    expandableListView.setEnabled(false);

        Helper.getListViewSize(listView);
        // Helper.getListViewSize(listView2);

         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
              //Toast.makeText(getActivity(), "hi this"+adapter.getItem(position), Toast.LENGTH_SHORT).show();


              int id=position;
                          try {
                                        String youtube=""+urls[id];
                                        Log.v("youtube",""+urls[id]);
                                         //start the youtube trailer on click @trailer
                                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(youtube)));
                                   }catch (ArrayIndexOutOfBoundsException aiob)
                                          {
                                             Toast.makeText(getActivity(), "Video not available", Toast.LENGTH_SHORT).show();
                                          }

          }
      });




        return rootView;






    }



    //update the list size for the total number of
    public static void updateList() {

       Helper.getListViewSize(listView);

    }

    public static void updateList2() {

        //Helper.getListViewSize(listView2);

    }






    //my code.......
    private void updateMovieDetail(String movieId) {
        try
        {
            FetchMovieTrailerData fetchMovieTrailerData = new FetchMovieTrailerData();
            FetchMovieReviewData fetchMovieReviewData = new FetchMovieReviewData();
            fetchMovieTrailerData.execute(movieId);
            fetchMovieReviewData.execute(movieId);
        }catch (Exception e)
        {
System.out.print(e);
        }

    }




}
