package com.example.sagarpreetchadha.topmovies;

import java.util.ArrayList;

/**
 * Created by sagarpreet chadha on 04-07-2016.
 */
public class MovieResponse {


    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    private  int total_pages ;
    private ArrayList<posterAnd_ID_data> results ;

    public ArrayList<posterAnd_ID_data> getResults() {
        return results;
    }

    public void setResults(ArrayList<posterAnd_ID_data> results) {
        this.results = results;
    }
}
