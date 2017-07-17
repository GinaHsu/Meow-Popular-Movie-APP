/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.meownamovie.utilities;

import com.example.android.meownamovie.MovieObj;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Utility functions to handle OpenMovieMap JSON data.
 */
public final class OpenMovieJsonUtils {
    private static final String TAG = OpenMovieJsonUtils.class.getName();

    private String curPage;
    private String totalPages;

    public  ArrayList<MovieObj> getMoviesUrlArrayFromJson (String movieJsonStr) throws JSONException {

        final String MOVIE_BASE = "http://image.tmdb.org/t/p/";
        final String POSTER_IMG_SIZE = "w185";

        final String Cur_PAGE = "page";
        final String TOTAL_PAGE = "total_pages";
        final String MV_LIST = "results";

        final String POSTER_PATH = "poster_path";
        final String ORI_TITLE = "original_title";
        final String VOTE_AVERAGE = "vote_average";
        final String RELEASE_DATE = "release_date";
        final String OVERVIEW = "overview";

        JSONObject movieJson = new JSONObject(movieJsonStr);
        totalPages = movieJson.getString(TOTAL_PAGE);
        curPage = movieJson.getString(Cur_PAGE);
        JSONArray movieJsonArray = movieJson.getJSONArray(MV_LIST);

        ArrayList<MovieObj> movieObjArrayList = new ArrayList<>();

        for (int i = 0; i < movieJsonArray.length(); i++) {

            String movieTitle = movieJsonArray.getJSONObject(i).getString(ORI_TITLE);
            String movieReleaseDate = movieJsonArray.getJSONObject(i).getString(RELEASE_DATE);
            String movieRated = movieJsonArray.getJSONObject(i).getString(VOTE_AVERAGE);
            String movieOverview = movieJsonArray.getJSONObject(i).getString(OVERVIEW);

            String posterPath = movieJsonArray.getJSONObject(i).getString(POSTER_PATH);
            String movieUrl = MOVIE_BASE + POSTER_IMG_SIZE + posterPath;

            MovieObj movieObj = new MovieObj();

            movieObj.setMovieTitle(movieTitle);
            movieObj.setMovieUrl(movieUrl);
            movieObj.setMovieReleaseDate(movieReleaseDate);
            movieObj.setMovieOverview(movieOverview);
            movieObj.setMovieRated(movieRated);
            movieObjArrayList.add(movieObj);
        }

        return movieObjArrayList;
    }

    public String getPageIdFromJson() {
        return curPage;
    }

    public String getTotalPageFromJson() {
        return totalPages;
    }

}

