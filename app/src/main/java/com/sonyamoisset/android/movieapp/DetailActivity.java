package com.sonyamoisset.android.movieapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.sonyamoisset.android.movieapp.utils.Constants;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.movie_poster)
    ImageView moviePoster;
    @BindView(R.id.movie_backdrop_path)
    ImageView movieBackdropPath;
    @BindView(R.id.movie_original_title)
    TextView movieName;
    @BindView(R.id.movie_overview)
    TextView moviePlot;
    @BindView(R.id.movie_vote_average)
    TextView movieVoteAverage;
    @BindView(R.id.movie_release_date)
    TextView movieReleaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        createUI();
    }

    private void createUI() {

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            if (intent.hasExtra(Constants.MOVIE_ORIGINAL_TITLE_KEY)) {

                String movie_name = extras.getString(Constants.MOVIE_ORIGINAL_TITLE_KEY);
                String movie_synopsis = extras.getString(Constants.MOVIE_OVERVIEW_KEY);
                String movie_rating = extras.getString(Constants.MOVIE_VOTE_AVERAGE_KEY);
                String movie_release_date = extras.getString(Constants.MOVIE_RELEASE_DATE_KEY);
                String movie_poster = extras.getString(Constants.MOVIE_POSTER_PATH_KEY);
                String movie_backdrop_path = extras.getString(Constants.MOVIE_POSTER_BACKDROP_PATH_KEY);

                String poster = Constants.MOVIE_POSTER_PATH;
                String backdrop = Constants.MOVIE_BACKDROP_IMAGE_PATH;

                Picasso.with(this)
                        .load(poster + movie_poster)
                        .placeholder(R.color.colorSecondary)
                        .into(moviePoster);

                Picasso.with(this)
                        .load(backdrop + movie_backdrop_path)
                        .placeholder(R.color.colorSecondary)
                        .into(movieBackdropPath);

                movieName.setText(movie_name);
                moviePlot.setText(movie_synopsis);
                movieVoteAverage.setText(movie_rating);
                movieReleaseDate.setText(movie_release_date);

                ActionBar actionBar = getSupportActionBar();
                if (actionBar != null) {
                    actionBar.setTitle(movie_name);
                }
            }
        }
    }
}
