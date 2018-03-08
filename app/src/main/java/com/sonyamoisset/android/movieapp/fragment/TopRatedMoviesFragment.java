package com.sonyamoisset.android.movieapp.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sonyamoisset.android.movieapp.R;

public class TopRatedMoviesFragment extends Fragment {

    public static TopRatedMoviesFragment newInstance() {
        return new TopRatedMoviesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_top_rated_movies, container, false);
    }
}
