package com.mte.infrastructurebase.utils

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Handler
import android.util.Log
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target


object  UtilsImageLoader {

    private val TAG: String? = "UtilsImageLoader"

    @SuppressLint("LogNotTimber")
    open fun loadImageUrl(view: ImageView, imageUrl: String?, errorResId : Drawable? = null) {

        Log.e(TAG, "loadImage $imageUrl")

        try {
            val context = view.context

//            var parent = (view.parent as ViewGroup)
//
//            val progressLoading =
//                LayoutInflater.from(context).inflate(R.layout.layout_loading_dialog_default, null)
//                    .apply {
//                        layoutParams = view.layoutParams
//                    }
////
//            parent.run {
//                removeView(view)
////                addView(progressLoading)
//            }

            val glideCallback = object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {

                    Handler().post {

                        if(errorResId != null ) {
                            view.setImageDrawable(errorResId)
                        }

//                        parent.run {
////                            removeView(progressLoading)
////                            addView(view)
//                        }
                    }
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    Handler().post {
                        view.setImageDrawable(resource)
//                        parent.run {
////                            removeView(progressLoading)
////                            addView(view)
//                        }
                    }
                    return true
                }
            }

            val options = RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .circleCrop()

            Glide.with(context)
                .load(imageUrl)
                .apply(options)
                .listener(glideCallback)
                .into(view)

        }catch (ex : java.lang.Exception){
            ex.printStackTrace()
        }


    }

    @SuppressLint("LogNotTimber")
    @JvmStatic
    @BindingAdapter("bind:drawableId")
     fun loadDrawable(view: ImageView, @DrawableRes drawableId: Int?) {

        Log.e(TAG, "loadDrawable $drawableId")

        try {

            view.let {
                it.setImageDrawable(if(drawableId == null) null else ContextCompat.getDrawable(it.context , drawableId))
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }


    }

    @SuppressLint("LogNotTimber")
    @JvmStatic
    @BindingAdapter("bind:drawableId")
     fun loadImageUri(view: ImageView, uri: Uri?) {

        Log.e(TAG, "loadImageUri $uri")

        try {
            view.setImageURI(uri)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }


    }
}
