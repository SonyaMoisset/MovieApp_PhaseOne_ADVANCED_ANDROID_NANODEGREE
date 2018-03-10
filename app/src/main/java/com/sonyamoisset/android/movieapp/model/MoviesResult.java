package com.sonyamoisset.android.movieapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesResult {
    @SerializedName("results")
    private final List<Movie> results;

    public MoviesResult(List<Movie> results) {
        this.results = results;
    }

    public List<Movie> getResults() {
        return results;
    }

}
