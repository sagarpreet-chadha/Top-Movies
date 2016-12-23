package com.example.sagarpreetchadha.topmovies;

/**
 * Created by sagarpreet chadha on 02-07-2016.
 */
public class reviewsdata {
    String review ;
    String author ;

    public reviewsdata(String review, String author) {
        this.review = review;
        this.author = author;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
