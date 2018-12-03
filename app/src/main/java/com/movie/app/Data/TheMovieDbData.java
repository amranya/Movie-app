package com.movie.app.Data;

import android.content.Context;
import android.widget.Toast;

import com.movie.app.API.TheMovieDbAPI;
import com.movie.app.Adapter.MovieAdapter;
import com.movie.app.Model.MovieDiscoverModel;
import com.movie.app.Model.MovieModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by iyadz_000 on 12/2/2018.
 */

public class TheMovieDbData {

    public enum Sort{
        RELEASE_DATE_DESC, POPULARITY_DESC, VOTE_AVERAGE_DESC
    }

    private static final String BASE_URL = "https://api.themoviedb.org/";
    private static final String LANGUAGE = "en-US";
    private static final String API_KEY = "0abccb29ed37027f09338ae4eca411c7";
    private static String SORT = "release_date.desc";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private static TheMovieDbAPI theMovieDbAPI = retrofit.create(TheMovieDbAPI.class);

    private static boolean isFirstLoad = true;
    private static boolean isLoading = false;
    private static List<MovieModel> movies = new ArrayList<>();
    private static int page = 1;


    public static void getLastMoviesData(final Context ctx, final MovieAdapter adapter) {

        if(isFirstLoad){
            adapter.updateList(movies);
            isFirstLoad = false;
        }

        if(!isLoading) {

            isLoading = true;

            adapter.addProgressBar();


            Call<MovieDiscoverModel> call = theMovieDbAPI.getLastMovies(API_KEY, LANGUAGE, SORT, page);
            call.enqueue(new Callback<MovieDiscoverModel>() {
                @Override
                public void onResponse(Call<MovieDiscoverModel> call, Response<MovieDiscoverModel> response) {

                    isLoading = false;

                    if (response != null) {

                        //Toast.makeText(ctx, "page number: " + String.valueOf(page), Toast.LENGTH_SHORT).show();

                        movies.remove(movies.size() - 1);
                        adapter.notifyItemRemoved(movies.size() - 1);

                        List<MovieModel> moviesNew = response.body().getMovies();
                        movies.addAll(moviesNew);

                        adapter.notifyDataSetChanged();

                        page++;


                    } else {
                        Toast.makeText(ctx, "no more data to display", Toast.LENGTH_SHORT).show();
                    }
                    
                }

                @Override
                public void onFailure(Call<MovieDiscoverModel> call, Throwable t) {

                }
            });
        }

    }

    public static void filter(Context ctx, MovieAdapter adapter, Sort sort){

        page = 1;
        movies.clear();
        adapter.notifyDataSetChanged();

        switch (sort){

            case RELEASE_DATE_DESC: SORT = "release_date.desc";
                break;
            case POPULARITY_DESC: SORT = "popularity.desc";
                break;
            case VOTE_AVERAGE_DESC: SORT = "vote_average.desc";
                break;

        }

        getLastMoviesData(ctx, adapter);


    }

}
