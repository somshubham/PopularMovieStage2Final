package com.movie.som.popularmoviestage2.databaseHelper;

/**
 * Created by som on 21/09/16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "movieOfflineDb2";
    private static final String TABLE_MOVIES = "movie";
    private static final String KEY_ID = "id";
    private static final String KEY_MOVIE_ID= "movie_id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_OVERVIEW = "overview";
    private static final String KEY_RELEASE_DATE = "release_date";
    private static final String KEY_VOTE_AVERAGE = "vote_average";
    private static final String KEY_POSTER = "poster";
    private static final String KEY_BACKDROP_PATH = "backdrop_image";
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MOVIES_TABLE = "CREATE TABLE " + TABLE_MOVIES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_MOVIE_ID + " TEXT,"+KEY_TITLE+" TEXT,"
                + KEY_OVERVIEW + " TEXT," +KEY_VOTE_AVERAGE+" TEXT," +KEY_RELEASE_DATE+" TEXT," +KEY_POSTER+" TEXT," +KEY_BACKDROP_PATH+" TEXT"+")";
        db.execSQL(CREATE_MOVIES_TABLE);
        Log.v("moviedb created",": ok");
    }
//+KEY_RELEASE_DATE+" TEXT,"+ ")";
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);

        // Create tables again
        onCreate(db);
    }

    // code to add the new Movies
  public  void addMovie(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MOVIE_ID, movie.getMovie_id()); //movie id
        values.put(KEY_TITLE, movie.getTitle()); // title
        values.put(KEY_OVERVIEW,movie.getOverview());// overview
        values.put(KEY_VOTE_AVERAGE,movie.getVote_average());//voter average
        values.put(KEY_RELEASE_DATE,movie.getRelease_date());//release date
      values.put(KEY_POSTER,movie.getPoster());//release date
      values.put(KEY_BACKDROP_PATH,movie.getBackdrop_image());//release date

        // Inserting Row
        db.insert(TABLE_MOVIES, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }


    // code to get the single movie
   public  Movie getMovie(Movie movie) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MOVIES, new String[] { KEY_ID,
                        KEY_MOVIE_ID, KEY_TITLE,KEY_OVERVIEW,KEY_VOTE_AVERAGE,KEY_RELEASE_DATE }, KEY_MOVIE_ID + "=?",
                new String[] { String.valueOf(movie.getMovie_id()) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Movie movieData = new Movie(Integer.parseInt(cursor.getString(0)),cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7));
        // return movie

        return movieData;
    }

    // code to get all movies in a list view
    public List<Movie> getAllMovies() {
        List<Movie> movieList = new ArrayList<Movie>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MOVIES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Movie movie = new Movie();
                movie.setId(Integer.parseInt(cursor.getString(0)));
                movie.setMovie_id(cursor.getString(1));
                movie.setTitle(cursor.getString(2));
                movie.setOverview(cursor.getString(3));
                movie.setVote_average(cursor.getString(4));
                movie.setRelease_date(cursor.getString(5));
                movie.setPoster(cursor.getString(6));
                movie.setBackdrop_image(cursor.getString(7));
                // Adding contact to list
                movieList.add(movie);
            } while (cursor.moveToNext());
        }

        // return movie list
        return movieList;
    }

    // code to update the single Movie
    public int updateMovie(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_MOVIE_ID, movie.getMovie_id()); //movie id
        values.put(KEY_TITLE, movie.getTitle()); // title
        values.put(KEY_OVERVIEW,movie.getOverview());// overview
        values.put(KEY_VOTE_AVERAGE,movie.getVote_average());//voter average
        values.put(KEY_RELEASE_DATE,movie.getRelease_date());//release date
        values.put(KEY_POSTER,movie.getPoster());//release date
        values.put(KEY_BACKDROP_PATH,movie.getBackdrop_image());//release date

        // updating row
        return db.update(TABLE_MOVIES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(movie.getId()) });
    }





    // Deleting single Movie
    public void deleteMovie(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MOVIES, KEY_MOVIE_ID + " = ?", new String[] { String.valueOf(movie.getMovie_id()) });
       // db.execSQL("delete from "+ TABLE_MOVIES);
        db.close();
    }



    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_MOVIES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}