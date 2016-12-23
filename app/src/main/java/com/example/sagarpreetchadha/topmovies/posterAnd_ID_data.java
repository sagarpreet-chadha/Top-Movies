package com.example.sagarpreetchadha.topmovies;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sagarpreet chadha on 28-06-2016.
 */
public class posterAnd_ID_data {
    @SerializedName("poster_path")
    private String posterpath ;
    private String  id ;
    @SerializedName("vote_count")
    private  int vote ;

    public posterAnd_ID_data(String posterpath, String id , int vote) {
        this.posterpath = posterpath;
        this.id = id;
        this.vote=vote ;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public String getPosterpath() {
        return posterpath;
    }

    public void setPosterpath(String posterpath) {
        this.posterpath = posterpath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
