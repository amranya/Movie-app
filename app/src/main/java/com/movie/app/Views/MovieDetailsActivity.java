package com.movie.app.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.movie.app.Model.MovieModel;
import com.movie.app.R;
import com.squareup.picasso.Picasso;


public class MovieDetailsActivity extends AppCompatActivity {

    private ImageView movieImage;
    private TextView movieName;
    private TextView movieOriginalName;
    private TextView movieOverview;
    private TextView movieReleaseDate;
    private TextView moviePopularity;
    private TextView movieVote;

    private MovieModel movie;

    private final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w600_and_h900_bestv2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        setUpUI();

        movie = getIntent().getParcelableExtra("movie");

        populateViews();

    }

    public void setUpUI(){

        movieImage = findViewById(R.id.movie_image);
        movieName = findViewById(R.id.movie_name);
        movieOriginalName = findViewById(R.id.movie_original_name);
        movieOverview = findViewById(R.id.overview);
        movieReleaseDate = findViewById(R.id.release_date);
        moviePopularity = findViewById(R.id.popularity);
        movieVote = findViewById(R.id.vote);

    }

    public void populateViews(){

        Picasso.get()
                .load(BASE_IMAGE_URL+movie.getMovieImage())
                .fit()
                .into(movieImage);
        movieName.append(movie.getMovieName());
        movieOriginalName.append(movie.getMovieOriginalName()+" ("+movie.getMovieOriginalLanguage()+")");
        movieOverview.append(movie.getMovieOverview());
        movieReleaseDate.append(movie.getMovieReleaseDate());
        moviePopularity.append(Double.toString(movie.getMoviePopularity()));
        movieVote.append(Double.toString(movie.getMovieAverageVote())+" of total ("+Integer.toString(movie.getMovieTotalVotes())+") votes");


    }
}
