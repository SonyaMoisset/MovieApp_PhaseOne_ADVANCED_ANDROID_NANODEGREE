package com.sonyamoisset.android.movieapp;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.sonyamoisset.android.movieapp.adapter.MoviesAdapter;
import com.sonyamoisset.android.movieapp.api.MoviesApiClient;
import com.sonyamoisset.android.movieapp.fragment.PopularMoviesFragment;
import com.sonyamoisset.android.movieapp.fragment.TopRatedMoviesFragment;
import com.sonyamoisset.android.movieapp.model.Movie;

import java.util.ArrayList;
import java.util.List;

import static com.sonyamoisset.android.movieapp.utils.NetworkConnectivity.isConnected;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isConnected(this)) {
            initViews();
            createBottomNavigationView();
        } else {
            Toast.makeText(this, R.string.message_no_connectivity, Toast.LENGTH_LONG).show();
        }
    }

    private void createBottomNavigationView() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;

                        switch (item.getItemId()) {

                            case R.id.sort_by__favorites_movies:
                                selectedFragment = PopularMoviesFragment.newInstance();
                                MoviesApiClient.getMovies(MainActivity.this,
                                        getString(R.string.main_activity_sortBy_popular_movies));
                                break;

                            case R.id.sort_by_popular_movies:
                                selectedFragment = PopularMoviesFragment.newInstance();
                                MoviesApiClient.getMovies(MainActivity.this,
                                        getString(R.string.main_activity_sortBy_popular_movies));
                                break;

                            case R.id.sort_by_top_rated_movies:
                                selectedFragment = TopRatedMoviesFragment.newInstance();
                                MoviesApiClient.getMovies(MainActivity.this,
                                        getString(R.string.main_activity_sortBy_top_rated_movies));
                                break;
                        }

                        FragmentTransaction transaction =
                                getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();

                        return true;
                    }
                });
    }

    private void initViews() {

        recyclerView = findViewById(R.id.recyclerView);

        Context context = this;
        List<Movie> movieList = new ArrayList<>();
        MoviesAdapter moviesAdapter = new MoviesAdapter(this, movieList);

        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(moviesAdapter);
        moviesAdapter.notifyDataSetChanged();

        MoviesApiClient.getMovies(this, getString(R.string.main_activity_sortBy_popular_movies));
    }
}
