package com.movie.app.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by iyadz_000 on 12/2/2018.
 */

public class MovieDiscoverModel {

    @SerializedName("results")
    private List<MovieModel> results = null;

    public List<MovieModel> getMovies() {
        return results;
    }

    public void setMovies(List<MovieModel> movies) {
        this.results = movies;
    }
}
