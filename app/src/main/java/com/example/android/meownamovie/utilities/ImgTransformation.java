package com.example.android.meownamovie.utilities;

import android.graphics.Bitmap;
import android.view.View;
import com.squareup.picasso.Transformation;


/**
 * Created by Gina on 7/10/17.
 */

public class ImgTransformation implements Transformation {

    private final View mView;

    public ImgTransformation(View view){ mView = view;}

    @Override public Bitmap transform(Bitmap source) {

        int width = mView.getWidth();

        int x = width /2;
        int y = source.getHeight() * x / source.getWidth();

        Bitmap result = Bitmap.createScaledBitmap(source,x,y,true);
        if (result != source) {
            source.recycle();
        }
        return result;
    }

    @Override public String key() { return "original()"; }
}
