package com.movie.som.popularmoviestage2.reviewCustomClass;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import com.movie.som.popularmoviestage2.BuildConfig;
import com.movie.som.popularmoviestage2.MovieDetailFragment;
import com.movie.som.popularmoviestage2.trailerCustomClass.MovieTrailer;

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
public class FetchMovieReviewData extends AsyncTask<String , Void,String[]> {


    public static   String [] size;
    public static  String[] author;
    public static String LOG_TAG = FetchMovieReviewData.class.getSimpleName();
    public static  String[] content;

    String k;

    public static  int jsonSize;
   public static  String[] getMovieReviewDataFromJson(String MovieJsonStr)
            throws JSONException {

        // These are the names of the JSON objects that need to be extracted.
        final String OWM_LIST = "results";
        final String id = "author";
        final String name = "content";


        JSONObject MovieJson = new JSONObject(MovieJsonStr);
        JSONArray MovieArray = MovieJson.getJSONArray(OWM_LIST);
        jsonSize=MovieArray.length();

        String[] resultStrs=new String[jsonSize] ;
        author=new String[jsonSize];
        content = new String[jsonSize];

        int count=0;
        for(int i = 0; i < MovieArray.length(); i++) {


            // Get the JSON object representing the movie
            JSONObject moviearraydata = MovieArray.getJSONObject(i);


            author[i] = moviearraydata.getString(id);
            content[i] = moviearraydata.getString(name);
            //size[i]=author[i];

        }


       //MovieDetailFragment.adapterReview.clear();
        for (int i=0;i<MovieArray.length();i++  ) {

      //   MovieDetailFragment.reviewCount.add(new MovieReview(""+author[i],""));
            Log.v(LOG_TAG, "author name: " + author[i]);
        }
        return  author;

    }

    @Override
    protected String[] doInBackground(String... params) {
        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.


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
                    "http://api.themoviedb.org/3/movie/"+s+"/reviews?api_key="+ BuildConfig.Movie_db_key;




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
            return getMovieReviewDataFromJson(MovieJsonStr);
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }




        return null;
    }






 @TargetApi(Build.VERSION_CODES.HONEYCOMB)
protected void onPostExecute(String[] strings) {

        Log.v("oknow","i am executing");
try {
    int j = 0;
    MovieDetailFragment.adapterReview.clear();
    Log.v("sizeofjson", "" + jsonSize);
    // for(int j=0;j<jsonSize;j++) {
    for (String s : strings) {
        MovieDetailFragment.reviewCount.add(new MovieReview("" + author[j], "" + content[j]));
        j++;
    }
    if (jsonSize == 0) {
        MovieDetailFragment.reviewCount.add(new MovieReview("", "no reviews"));
    }

//update the list size for the total number of the trailers .....................


    MovieDetailFragment.updateList2();


}catch (NullPointerException e)
{
    Log.v("gotgheexp",""+e);
}
    }




}










//my code.......








