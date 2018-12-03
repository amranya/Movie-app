package com.movie.app.Views;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.movie.app.Adapter.MovieAdapter;
import com.movie.app.Model.MovieModel;
import com.movie.app.R;
import com.squareup.picasso.Callback;
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

    String space = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        supportPostponeEnterTransition();

        movie = getIntent().getParcelableExtra("movie");

        setUpUI();

        populateViews();

    }

    public void setUpUI(){

        movieImage = findViewById(R.id.movie_image);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            movieImage.setTransitionName(getIntent().getExtras().getString("transitionName"));
        }
        movieName = findViewById(R.id.movie_name);
        movieOriginalName = findViewById(R.id.movie_original_name);
        movieOverview = findViewById(R.id.overview);
        movieReleaseDate = findViewById(R.id.release_date);
        moviePopularity = findViewById(R.id.popularity);
        movieVote = findViewById(R.id.vote);

    }

    public void populateViews(){

        Picasso.get()
                .load(MovieAdapter.BASE_IMAGE_URL + movie.getMovieImage())
                .placeholder(R.drawable.movie_placeholder)
                .noFade()
                .fit()
                .into(movieImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        supportStartPostponedEnterTransition();
                    }

                    @Override
                    public void onError(Exception e) {

                        supportStartPostponedEnterTransition();
                    }

                });
        movieName.append(space + movie.getMovieName());
        movieOriginalName.append(space + movie.getMovieOriginalName() + " ("
                               + movie.getMovieOriginalLanguage() + ")");
        movieOverview.append(space + movie.getMovieOverview());
        movieReleaseDate.append(space + movie.getMovieReleaseDate());
        moviePopularity.append(space + Double.toString(movie.getMoviePopularity()));
        movieVote.append(space + Double.toString(movie.getMovieAverageVote()) + " of total ("
                         + Integer.toString(movie.getMovieTotalVotes()) + ") votes");

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.finishAfterTransition();
        }
        else{this.finish();}

    }
}
