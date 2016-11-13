package com.movie.som.popularmoviestage2.databaseHelper;

/**
 * Created by som on 22/09/16.
 */
public class Movie {
    public int getId() {
        return id;
    }

    Movie()
    {

    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getVote_average() {
        return vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getMovie_id() {
        return movie_id;
    }

    public String getPoster() {
        return poster;
    }
    public String getBackdrop_image() {
        return backdrop_image;
    }


    public Movie(String movie_id,String title, String overview, String vote_average, String release_date,String poster,String backdrop_image) {
        this.title = title;
        this.overview = overview;
        this.vote_average = vote_average;
        this.release_date = release_date;
        this.movie_id = movie_id;
        this.backdrop_image=backdrop_image;
        this.poster=poster;
    }

    int id;
    String title;
    String overview;
    String vote_average;
    String release_date;
    String movie_id;





    String poster;
    String backdrop_image;

    public Movie(int id,String movie_id,String title, String overview, String vote_average, String release_date,String poster,String backdrop_image) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.vote_average = vote_average;
        this.release_date = release_date;
        this.movie_id = movie_id;
        this.poster=poster;
        this.backdrop_image=backdrop_image;
    }




    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }


    public void setPoster(String poster) {
        this.poster = poster;
    }



    public void setBackdrop_image(String backdrop_image) {
        this.backdrop_image = backdrop_image;
    }

}
