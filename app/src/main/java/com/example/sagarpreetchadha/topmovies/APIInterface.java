package com.example.sagarpreetchadha.topmovies;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by sagarpreet chadha on 04-07-2016.
 */
public interface APIInterface
{
    @GET("/3/discover/movie?sort_by=popularity.desc&api_key=4a350fc6944dd3e7db84f4b96b79ce94")
    Call<MovieResponse> getMovies() ;
}
