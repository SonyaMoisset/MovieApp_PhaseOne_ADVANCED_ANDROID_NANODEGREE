package com.sonyamoisset.android.movieapp.model;

import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("poster_path")
    private final String posterPath;
    @SerializedName("overview")
    private final String overview;
    @SerializedName("release_date")
    private final String releaseDate;
    @SerializedName("original_title")
    private final String originalTitle;
    @SerializedName("backdrop_path")
    private final String backdropPath;
    @SerializedName("vote_average")
    private final Double voteAverage;

    public Movie(String posterPath, String overview, String releaseDate,
                 String originalTitle, String backdropPath, Double voteAverage) {
        this.posterPath = posterPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.originalTitle = originalTitle;
        this.backdropPath = backdropPath;
        this.voteAverage = voteAverage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

}
