package com.example.android.meownamovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.meownamovie.utilities.ImgTransformation;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView mImageView;
    private RelativeLayout rlMovieDetail;
    private TextView mReleaseDate;
    private TextView mTitle;
    private TextView mDescription;
    private TextView mRated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        rlMovieDetail = (RelativeLayout) findViewById(R.id.rl_movie_detail);
        mImageView = (ImageView) findViewById(R.id.tv_movie_img);
        mTitle = (TextView)findViewById(R.id.tv_title);
        mReleaseDate = (TextView)findViewById(R.id.tv_movie_release_date);
        mRated = (TextView)findViewById(R.id.tv_movie_rated);
        mDescription = (TextView)findViewById(R.id.tv_movie_description);

        Intent intent = getIntent();
        if (intent != null) {
            setMovieObj((MovieObj)intent.getParcelableExtra("MovieObj"));
        }

    }

    private void setMovieObj(MovieObj movieObj) {

        Picasso.with(this)
                .load(movieObj.getMovieUrl())
                .transform(new ImgTransformation(rlMovieDetail.getRootView()))
                .into(mImageView);

        // TODO: update the UI components using MovieObj
        mTitle.setText(movieObj.getMovieTitle());
        mDescription.setText(movieObj.getMovieOverview());
        mReleaseDate.setText(movieObj.getMovieReleaseDate());
        mRated.setText(movieObj.getMovieRated());
    }
}
