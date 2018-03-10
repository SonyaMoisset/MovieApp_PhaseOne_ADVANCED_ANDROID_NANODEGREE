package com.sonyamoisset.android.movieapp.api;

import com.sonyamoisset.android.movieapp.model.MoviesResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface MoviesApiInterface {

    @GET(MoviesApiParams.POPULAR_PATH)
    Call<MoviesResult> getPopularMovies(@Query("api_key") String apiKey);

    @GET(MoviesApiParams.TOP_RATED_PATH)
    Call<MoviesResult> getTopRatedMovies(@Query("api_key") String apiKey);
}
