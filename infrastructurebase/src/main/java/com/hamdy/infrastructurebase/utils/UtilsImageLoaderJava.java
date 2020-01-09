package com.hamdy.infrastructurebase.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;


/**
 * Created by Your name on 12/9/2019.
 */
public class UtilsImageLoaderJava {

    public static  void loadImage(final ImageView imageView , @DrawableRes final int errorRes , String url){


        if(url == null || url.isEmpty()) return;

        final Context context = imageView.getContext() ;


        final FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams(imageView.getLayoutParams());
        frameLayout.setId(imageView.getId());

        final ProgressBar progressBar = new ProgressBar(context);
        progressBar.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT , ViewGroup.LayoutParams.WRAP_CONTENT , Gravity.CENTER));
        frameLayout.addView(progressBar);

        final ViewGroup parent = (ViewGroup) imageView.getParent();

        parent.removeView(imageView);
        parent.addView(frameLayout);


        RequestListener glideCallback = new RequestListener<Drawable>(){

            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageDrawable(ContextCompat.getDrawable(context , errorRes));
                        parent.removeView(frameLayout);
                        parent.removeView(imageView);
                        parent.addView(imageView);

                    }
                });
                return false;
            }

            @Override
            public boolean onResourceReady(final Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageDrawable(resource);
                        parent.removeView(frameLayout);
                        parent.removeView(imageView);
                        parent.addView(imageView);

                    }
                });
                return true;
            }
        };


        RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .circleCrop();

        Glide.with(context)
                .load(url)
                .listener(glideCallback)
                .into(imageView);


    }
}
