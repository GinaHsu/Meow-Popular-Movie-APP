package com.example.android.meownamovie;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.meownamovie.utilities.NetworkUtils;
import com.example.android.meownamovie.utilities.OpenMovieJsonUtils;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GridView gridview;
    private ImageAdapter mImageAdapter;
    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);
        gridview = (GridView) findViewById(R.id.grid_view);

        mImageAdapter = new ImageAdapter(this);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        gridview.setAdapter(mImageAdapter);

        loadMovieData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();
        String userOptionFilter;

        switch (itemId) {
            /*
             * When you click the reset menu item, we want to start all over
             * and display the pretty gradient again. There are a few similar
             * ways of doing this, with this one being the simplest of those
             * ways. (in our humble opinion)
             */
            case R.id.popular_movie:
                mImageAdapter = new ImageAdapter(this);
                mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
                gridview.setAdapter(mImageAdapter);
                loadMovieData();
                return true;

            case R.id.top_rated:
                mImageAdapter = new ImageAdapter(this);
                mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
                gridview.setAdapter(mImageAdapter);
                userOptionFilter = "top_rated";
                loadMovieData(userOptionFilter);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadMovieData(){
        showMovieDataView();
        String userDefaultFilter = "popular";

        new FetchMovieTask().execute(userDefaultFilter);
    }

    private void loadMovieData(String userOptionFilter){
        showMovieDataView();
        new FetchMovieTask().execute(userOptionFilter);
    }

    /**
     * This method will make the View for the movie data visible and
     * hide the error message.
     * <p>
     * Since it is okay to redundantly set the visibility of a View, we don't
     * need to check movie each view is currently visible or invisible.
     */
    private void showMovieDataView() {
        /* First, make sure the error is invisible */
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        /* Then, make sure the weather data is visible */
        gridview.setVisibility(View.VISIBLE);
    }

    /**
     * This method will make the error message visible and hide the weather
     * View.
     * <p>
     * Since it is okay to redundantly set the visibility of a View, we don't
     * need to check movie each view is currently visible or invisible.
     */
    private void showErrorMessage() {
        /* First, hide the currently visible data */
        gridview.setVisibility(View.INVISIBLE);
        /* Then, show the error */
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    private class FetchMovieTask extends AsyncTask<String, Void, MovieTable>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected MovieTable doInBackground(String... params) {

            /* If there's no user option input , there's nothing to look up. */
            if (params.length == 0) {
                return null;
            }

            String option = params[0];
            URL movieRequestUrl = NetworkUtils.buildUrl(option);


            try{
                String jsonMovie = NetworkUtils
                        .getResponseFromHttpUrl(movieRequestUrl);

                OpenMovieJsonUtils movieJsonUtils = new OpenMovieJsonUtils();

                ArrayList<MovieObj> movieData = movieJsonUtils.getMoviesUrlArrayFromJson(jsonMovie);
                String curPage = movieJsonUtils.getPageIdFromJson();
                String totalPage= movieJsonUtils.getTotalPageFromJson();

                MovieTable movieDataTable = new MovieTable();

                movieDataTable.setCurPage(curPage);
                movieDataTable.setTotalPages(totalPage);
                movieDataTable.setMovieObjArray(movieData);

                return movieDataTable;

            }catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(MovieTable movieData) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);

            if (movieData != null) {
                showMovieDataView();
                mImageAdapter.setMovieData(movieData);
            } else {
                showErrorMessage();
            }
        }
    }

}
