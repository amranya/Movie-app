package com.movie.app.Data;

import android.content.Context;
import android.os.Handler;
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

    private static final String BASE_URL = "https://api.themoviedb.org/";
    private static final String LANGUAGE = "en-US";
    private static final String API_KEY = "0abccb29ed37027f09338ae4eca411c7";
    private static final String SORT = "release_date.desc";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private static TheMovieDbAPI theMovieDbAPI = retrofit.create(TheMovieDbAPI.class);

    private static List<MovieModel> movies = new ArrayList<>();
    private static int page = 1;


    public static void getLastMoviesData(final Context ctx, final MovieAdapter adapter) {

        adapter.updateList(movies);
        Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            public void run() {

                movies.add(null);
                adapter.notifyItemInserted(movies.size() - 1);

            }
        };
        handler.post(runnable);


        Call<MovieDiscoverModel> call = theMovieDbAPI.getLastMovies(API_KEY, LANGUAGE, SORT, page);
        call.enqueue(new Callback<MovieDiscoverModel>() {
            @Override
            public void onResponse(Call<MovieDiscoverModel> call, Response<MovieDiscoverModel> response) {

                if (response != null) {

                    Toast.makeText(ctx, "page number: " + String.valueOf(page), Toast.LENGTH_SHORT).show();

                    movies.remove(movies.size() - 1);
                    adapter.notifyItemRemoved(movies.size());

                    List<MovieModel> moviesNew = response.body().getMovies();
                    movies.addAll(moviesNew);

                    adapter.notifyItemInserted(movies.size() - 1);

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
