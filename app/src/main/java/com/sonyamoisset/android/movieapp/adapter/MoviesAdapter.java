package com.sonyamoisset.android.movieapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sonyamoisset.android.movieapp.DetailActivity;
import com.sonyamoisset.android.movieapp.R;
import com.sonyamoisset.android.movieapp.model.Movie;
import com.sonyamoisset.android.movieapp.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private final Context context;
    private final List<Movie> movieList;

    public MoviesAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        private final ImageView moviePoster;

        private MovieViewHolder(View view) {
            super(view);
            moviePoster = view.findViewById(R.id.movie_poster);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION) {

                        Intent intent = new Intent(context, DetailActivity.class);
                        Bundle extras = new Bundle();

                        extras.putString(Constants.MOVIE_ORIGINAL_TITLE_KEY,
                                movieList.get(position).getOriginalTitle());
                        extras.putString(Constants.MOVIE_POSTER_PATH_KEY,
                                movieList.get(position).getPosterPath());
                        extras.putString(Constants.MOVIE_POSTER_BACKDROP_PATH_KEY,
                                movieList.get(position).getBackdropPath());
                        extras.putString(Constants.MOVIE_OVERVIEW_KEY,
                                movieList.get(position).getOverview());
                        extras.putString(Constants.MOVIE_VOTE_AVERAGE_KEY,
                                Double.toString(movieList.get(position).getVoteAverage()));
                        extras.putString(Constants.MOVIE_RELEASE_DATE_KEY,
                                movieList.get(position).getReleaseDate());

                        intent.putExtras(extras);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        context.startActivity(intent);
                    }
                }
            });
        }
    }

    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_movie, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesAdapter.MovieViewHolder holder, int position) {
        String poster = Constants.MOVIE_POSTER_PATH + movieList.get(position).getPosterPath();

        Picasso.with(context)
                .load(poster)
                .placeholder(R.color.colorSecondary)
                .into(holder.moviePoster);
    }

    @Override
    public int getItemCount() {
        return (movieList == null) ? 0 : movieList.size();
    }
}
