package com.movie.app.Views;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.Toast;

import com.movie.app.Adapter.MovieAdapter;
import com.movie.app.Data.DataTest;
import com.movie.app.Data.TheMovieDbData;
import com.movie.app.Model.MovieModel;
import com.movie.app.R;
import com.movie.app.Utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager manager;
    MovieAdapter adapter;
    List<MovieModel> movies = new ArrayList<>();

    boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Utils.initializeSSLContext(this);

        recyclerView = findViewById(R.id.my_recycler_view);
        manager = new LinearLayoutManager(this);
        adapter = new MovieAdapter(this, movies);

        getData();

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = manager.getChildCount();
                totalItems = manager.getItemCount();
                scrollOutItems = manager.findFirstVisibleItemPosition();

                if (isScrolling && (currentItems + scrollOutItems >= totalItems)) {
                    isScrolling = false;
                    getData();

                }
            }
        });

    }

    public void getData() {
        TheMovieDbData.getLastMoviesData(this, adapter);
        //DataTest.getDataTest(this, adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.popularity){
            TheMovieDbData.filter(this, adapter, TheMovieDbData.Sort.POPULARITY_DESC);
            Toast.makeText(this, "by popularity", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.rating){
            TheMovieDbData.filter(this, adapter, TheMovieDbData.Sort.VOTE_AVERAGE_DESC);
            Toast.makeText(this, "by top voted", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.release){
            TheMovieDbData.filter(this, adapter, TheMovieDbData.Sort.RELEASE_DATE_DESC);
            Toast.makeText(this, "by newest", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
