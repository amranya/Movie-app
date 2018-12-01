package com.movie.app.Data;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.movie.app.Adapter.MovieAdapter;
import com.movie.app.Model.MovieModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iyadz_000 on 12/1/2018.
 */

public class DataTest {

    private static List<MovieModel> movies = new ArrayList<>();
    private static int page = 1;

    public static void getDataTest(final Context ctx, final MovieAdapter adapter) {

        adapter.updateList(movies);
        movies.add(null);
        adapter.notifyItemInserted(movies.size() - 1);

        Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            public void run() {

                Toast.makeText(ctx, "page number: " + String.valueOf(page), Toast.LENGTH_SHORT).show();

                movies.remove(movies.size() - 1);
                adapter.notifyItemRemoved(movies.size());

                //populating adapter test

                for (int i = 0; i < 10; i++) {

                    MovieModel movie = new MovieModel();
                    movie.setMovieName("movie " + i + " page " + page);
                    movie.setMovieImage("http://i.imgur.com/DvpvklR.png");

                    movies.add(movie);

                    adapter.notifyItemInserted(movies.size() - 1);

                }

                page++;
            }
        };
        handler.postDelayed(runnable, 5000);


    }

}
