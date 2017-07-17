package com.example.android.meownamovie;

import java.util.ArrayList;

/**
 * Created by Gina on 7/12/17.
 */

public class MovieTable {
    private ArrayList<MovieObj> mMovieObjArrayList = new ArrayList<>();
    private String curPage;
    private String totalPages;

    public void setCurPage(String curPage) {
        this.curPage = curPage;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    public void setMovieObjArray(ArrayList<MovieObj> mMovieObjArrayList) {
        this.mMovieObjArrayList = mMovieObjArrayList;
    }

    public ArrayList<MovieObj> getMovieObjArrayList() {
        return mMovieObjArrayList;
    }

}
