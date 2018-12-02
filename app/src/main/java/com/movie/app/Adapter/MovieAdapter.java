package com.movie.app.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.movie.app.Model.MovieModel;
import com.movie.app.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by iyadz_000 on 12/1/2018.
 */


public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0, VIEW_TYPE_LOADING = 1;
    private Context context;
    private List<MovieModel> movies;
    private final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w600_and_h900_bestv2";

    public MovieAdapter(Context context, List<MovieModel> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public int getItemViewType(int position) {

        return movies.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        if (viewType == VIEW_TYPE_ITEM) {

            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.movie_row, parent, false);
            return new MovieViewHolder(view);

        } else if (viewType == VIEW_TYPE_LOADING) {

            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.progress_bar, parent, false);
            return new LoadingHolder(view);

        }

        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        if (holder instanceof MovieViewHolder) {

            MovieViewHolder movieViewHolder = (MovieViewHolder) holder;

            MovieModel movie = movies.get(position);
            movieViewHolder.movieTitle.setText(movie.getMovieName());
            Picasso.get()
                    .load(BASE_IMAGE_URL+movie.getMovieImage())
                    .resize(60, 60)
                    .placeholder(R.drawable.movie_placeholder)
                    .into(movieViewHolder.movieImage);

        } else if (holder instanceof LoadingHolder) {

            LoadingHolder loadingHolder = (LoadingHolder) holder;

            loadingHolder.progressBar.setIndeterminate(true);


        }


    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void updateList(List<MovieModel> list) {

        movies = list;

    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView movieTitle;
        ImageView movieImage;

        public MovieViewHolder(View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.movie_name);
            movieImage = itemView.findViewById(R.id.movie_image);
        }

    }

    public class LoadingHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;

        public LoadingHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);

        }
    }

}