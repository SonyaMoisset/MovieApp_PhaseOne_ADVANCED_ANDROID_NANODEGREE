package com.sonyamoisset.android.movieapp.api;

import com.sonyamoisset.android.movieapp.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface MoviesApiInterface {

    @GET(MoviesApiParams.POPULAR_PATH)
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET(MoviesApiParams.TOP_RATED_PATH)
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);
}
