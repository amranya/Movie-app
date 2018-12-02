package com.movie.app.API;

import com.movie.app.Model.MovieDiscoverModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by iyadz_000 on 12/2/2018.
 */

public interface TheMovieDbAPI {


    @GET("3/discover/movie")
    Call<MovieDiscoverModel> getLastMovies(@Query("api_key") String api_key,
                                           @Query("language") String language,
                                           @Query("sort_by") String sort_by,
                                           @Query("page") int page);


}
