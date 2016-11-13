package com.movie.som.popularmoviestage2.trailerCustomClass;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import com.movie.som.popularmoviestage2.BuildConfig;
import com.movie.som.popularmoviestage2.MovieDetailFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by som on 19/09/16.
 */
public class FetchMovieTrailerData extends AsyncTask<String , Void,String[]> {



    public static  String[] trailername;
    public static String LOG_TAG = FetchMovieTrailerData.class.getSimpleName();

    int k;


   public static  String[] getMovieDataFromJson(String MovieJsonStr)
            throws JSONException {

        // These are the names of the JSON objects that need to be extracted.
        final String OWM_LIST = "results";
        final String id = "key";
        final String name = "name";


        JSONObject MovieJson = new JSONObject(MovieJsonStr);
        JSONArray MovieArray = MovieJson.getJSONArray(OWM_LIST);
        int k=MovieArray.length();

        String[] resultStrs=new String[k] ;
     MovieDetailFragment.urls=new String[k];
        trailername = new String[k];

        int count=0;
        for(int i = 0; i < MovieArray.length(); i++) {


            // Get the JSON object representing the movie
            JSONObject moviearraydata = MovieArray.getJSONObject(i);


            String idvalue = moviearraydata.getString(id);
            trailername[i] = moviearraydata.getString(name);



            MovieDetailFragment.urls[i]="http://www.youtube.com/watch?v=" +idvalue;


        }


        for (String s :  MovieDetailFragment.urls) {
            Log.v(LOG_TAG, "Movie id: " + s);

        }
        return  MovieDetailFragment.urls;

    }

    @Override
    protected String[] doInBackground(String... params) {
        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.

        // If there's no zip code, there's nothing to look up.  Verify size of params.
        if (params.length == 0) {
            return null;
        }



        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String MovieJsonStr = null;

        String format = "json";




        try {

            String s=""+params[0];

            final String Movie_BASE_URL2 =
                    "http://api.themoviedb.org/3/movie/"+s+"/videos?api_key="+ BuildConfig.Movie_db_key;




            URL url = new URL(Movie_BASE_URL2);

            Log.v(LOG_TAG, "Built URI " + Movie_BASE_URL2);

            // Create the request to themoviedb, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            MovieJsonStr = buffer.toString();



            Log.v("Movie",""+MovieJsonStr);
            Log.v(LOG_TAG, "Movie string: " + MovieJsonStr);


        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attemping
            // to parse it.
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }

        try {
            return getMovieDataFromJson(MovieJsonStr);
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }




        return null;
    }






    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    protected void onPostExecute(String[] strings) {

        if (strings != null) {
            //adding the number to  add the number of trailers for the movies ........
            int i=0;
            MovieDetailFragment.adapter.clear();
            //  ArrayList<MovieTrailer> newUsers = new ArrayList<MovieTrailer>();
            Log.v("trailerjsonarray",""+k);
            for (String s : strings) {
                Log.v("mylogfile", "trailer id: " + s);
               MovieDetailFragment.trailersCount.add(new MovieTrailer(""+trailername[i]));
               //  MovieDetailFragment.adapter.add("Trailer "+i);
                i++;

            }


//update the list size for the total number of the trailers .....................
       MovieDetailFragment.updateList();

        }

    }




}










//my code.......








