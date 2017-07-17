package com.example.android.meownamovie;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.android.meownamovie.utilities.ImgTransformation;
import com.squareup.picasso.Picasso;


/**
 * Created by Gina on 7/9/17.
 */

class ImageAdapter extends BaseAdapter{

    //private final List<String> urls = new ArrayList<>();
    private MovieTable mMovieData = null;
    private Activity mActivity = null;

    public ImageAdapter(Activity activity) {
        mActivity = activity;
    }

    class MyOnClickListener implements View.OnClickListener {
        final MovieObj mMovieObj;
        final Context mContext;

        public MyOnClickListener(MovieObj movieObj, Context context) {
            mMovieObj = movieObj;
            mContext = context;
        }
        @Override
        public void onClick(View view) {
            //Log.d(TAG, "clicked on "+mMovieObj.getMovieUrl());
            startMovieDetailActivity(mMovieObj);
        }
    }

    private void startMovieDetailActivity(MovieObj movieObj) {
        Intent intentToStartMovieDetailActivity = new Intent(mActivity, MovieDetailActivity.class);
        intentToStartMovieDetailActivity.putExtra("MovieObj", movieObj);
        mActivity.startActivity(intentToStartMovieDetailActivity);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(parent.getContext());
        } else {
            imageView = (ImageView) convertView;
        }

        MovieObj movieObj = mMovieData.getMovieObjArrayList().get(position);
        String url =  getItem(position);

        Picasso.with(parent.getContext())
                .load(url)
                .transform(new ImgTransformation(parent))
                .into(imageView);

        imageView.setOnClickListener(new MyOnClickListener(movieObj, parent.getContext()));

        return imageView;
    }

    @Override public int getCount() {
        int count = 0;
        if (mMovieData != null)
            count = mMovieData.getMovieObjArrayList().size();
        return count;
    }

    @Override public String getItem(int position) {
        String url = null;
        if (mMovieData != null)
            url = mMovieData.getMovieObjArrayList().get(position).getMovieUrl();
        return url;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setMovieData(MovieTable movieData) {
        mMovieData = movieData;
        notifyDataSetChanged();
   }
}



