package com.example.sagarpreetchadha.topmovies;

/**
 * Created by sagarpreet chadha on 29-06-2016.
 */
public class Detailed_data_content {

    String movieid ;
    String moviename ;

    public String getMovieid() {
        return movieid;
    }

    public void setMovieid(String movieid) {
        this.movieid = movieid;
    }

    public String getMoviename() {
        return moviename;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }

    public Detailed_data_content(String movieid, String moviename) {

        this.movieid = movieid;
        this.moviename = moviename;
    }
}
