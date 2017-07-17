package com.example.android.meownamovie;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Gina on 7/12/17.
 */

public class MovieObj implements Parcelable {

    private String mMovieTitle;
    private String mMovieUrl;
    private String mMovieReleaseDate;
    private String mMovieOverview;
    private String mMovieRated;

    public void setMovieTitle(String mMovieTitle) {
        this.mMovieTitle = mMovieTitle;
    }
    public void setMovieUrl(String mMovieUrl) {
        this.mMovieUrl = mMovieUrl;
    }
    public void setMovieReleaseDate(String mMovieReleaseDate){ this.mMovieReleaseDate = mMovieReleaseDate; }
    public void setMovieOverview(String mMovieOverview) { this.mMovieOverview = mMovieOverview; }
    public void setMovieRated(String mMovieRated) { this.mMovieRated = mMovieRated; }

    public String getMovieTitle() {
        return mMovieTitle;
    }
    public String getMovieUrl() {
        return mMovieUrl;
    }
    public String getMovieReleaseDate() { return mMovieReleaseDate; }
    public String getMovieOverview() { return mMovieOverview; }
    public String getMovieRated() { return mMovieRated; }

    public MovieObj() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {
                this.mMovieTitle, this.mMovieUrl, this.mMovieReleaseDate,
                this.mMovieOverview, this.mMovieRated
        });
    }

    private MovieObj(Parcel in) {
        String[] data = new String[5];
        in.readStringArray(data);
        this.mMovieTitle = data[0];
        this.mMovieUrl = data[1];
        this.mMovieReleaseDate = data[2];
        this.mMovieOverview = data[3];
        this.mMovieRated = data[4];
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public MovieObj createFromParcel(Parcel in) {
            return new MovieObj(in);
        }

        public MovieObj[] newArray(int size) {
            return new MovieObj[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

}
