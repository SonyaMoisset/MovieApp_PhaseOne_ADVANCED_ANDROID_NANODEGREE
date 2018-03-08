package com.sonyamoisset.android.movieapp.api;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.sonyamoisset.android.movieapp.MainActivity;
import com.sonyamoisset.android.movieapp.R;
import com.sonyamoisset.android.movieapp.adapter.MoviesAdapter;
import com.sonyamoisset.android.movieapp.model.Movie;
import com.sonyamoisset.android.movieapp.model.MoviesResponse;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesApiClient {

    private static Retrofit retrofit = null;

    private static Retrofit getClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(MoviesApiParams.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    public static void getMovies(final MainActivity mainActivity, String sortBy) {
        try {
            if (MoviesApiParams.API_KEY.isEmpty()) {
                Toast.makeText(mainActivity.getApplicationContext(),
                        "Please obtain API Key from themoviedb.org",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            MoviesApiInterface moviesApiInterface =
                    getClient().create(MoviesApiInterface.class);

            Call<MoviesResponse> moviesResponse = null;

            if (Objects.equals(sortBy, mainActivity.getString(R.string.main_activity_sortBy_popular_movies))) {
                moviesResponse =
                        moviesApiInterface.getPopularMovies(MoviesApiParams.API_KEY);
            }
            if (Objects.equals(sortBy, mainActivity.getString(R.string.main_activity_sortBy_top_rated_movies))) {
                moviesResponse =
                        moviesApiInterface.getTopRatedMovies(MoviesApiParams.API_KEY);
            }

            if (moviesResponse != null) {
                moviesResponse.enqueue(new Callback<MoviesResponse>() {

                    @Override
                    public void onResponse(@NonNull Call<MoviesResponse> call,
                                           @NonNull Response<MoviesResponse> response) {
                        List<Movie> movies = response.body().getResults();
                        mainActivity.recyclerView.setAdapter(
                                new MoviesAdapter(mainActivity.getApplicationContext(), movies));
                        mainActivity.recyclerView.smoothScrollToPosition(0);
                    }

                    @Override
                    public void onFailure(@NonNull Call<MoviesResponse> call,
                                          @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
