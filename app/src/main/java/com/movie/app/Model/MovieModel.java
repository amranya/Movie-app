package com.movie.app.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by iyadz_000 on 12/1/2018.
 */

public class MovieModel implements Parcelable {

    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String movieName;
    @SerializedName("poster_path")
    private String movieImage;
    @SerializedName("original_title")
    private String movieOriginalName;
    @SerializedName("original_language")
    private String movieOriginalLanguage;
    @SerializedName("release_date")
    private String movieReleaseDate;
    @SerializedName("overview")
    private String movieOverview;
    @SerializedName("popularity")
    private double moviePopularity;
    @SerializedName("vote_average")
    private double movieAverageVote;
    @SerializedName("vote_count")
    private int movieTotalVotes;

    public MovieModel() {
    }

    protected MovieModel(Parcel in) {
        id = in.readInt();
        movieName = in.readString();
        movieImage = in.readString();
        movieOriginalName = in.readString();
        movieOriginalLanguage = in.readString();
        movieReleaseDate = in.readString();
        movieOverview = in.readString();
        moviePopularity = in.readDouble();
        movieAverageVote = in.readDouble();
        movieTotalVotes = in.readInt();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(String movieImage) {
        this.movieImage = movieImage;
    }
    public String getMovieOriginalName() {
        return movieOriginalName;
    }

    public void setMovieOriginalName(String movieOriginalName) {
        this.movieOriginalName = movieOriginalName;
    }

    public String getMovieOriginalLanguage() {
        return movieOriginalLanguage;
    }

    public void setMovieOriginalLanguage(String movieOriginalLanguage) {
        this.movieOriginalLanguage = movieOriginalLanguage;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }

    public double getMoviePopularity() {
        return moviePopularity;
    }

    public void setMoviePopularity(double moviePopularity) {
        this.moviePopularity = moviePopularity;
    }

    public double getMovieAverageVote() {
        return movieAverageVote;
    }

    public void setMovieAverageVote(double movieAverageVote) {
        this.movieAverageVote = movieAverageVote;
    }

    public int getMovieTotalVotes() {
        return movieTotalVotes;
    }

    public void setMovieTotalVotes(int movieTotalVotes) {
        this.movieTotalVotes = movieTotalVotes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(movieName);
        parcel.writeString(movieImage);
        parcel.writeString(movieOriginalName);
        parcel.writeString(movieOriginalLanguage);
        parcel.writeString(movieReleaseDate);
        parcel.writeString(movieOverview);
        parcel.writeDouble(moviePopularity);
        parcel.writeDouble(movieAverageVote);
        parcel.writeInt(movieTotalVotes);
    }
}
