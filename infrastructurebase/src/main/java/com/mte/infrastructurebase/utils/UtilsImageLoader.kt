package com.hamdy.infrastructurebase.utils

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.mte.infrastructurebase.R
import com.mte.infrastructurebase.databinding.LayoutLoadingDialogDefaultBinding
import com.mte.infrastructurebase.utils.LocaleHelperJava


object  UtilsImageLoader {

    private val TAG: String? = "UtilsImageLoader"

    @SuppressLint("LogNotTimber")
    open fun loadImageUrl(
        imageView: ImageView,
        imageUrl: String?,
        error: Drawable? = null,
        placeholder: Drawable? = null,
        isProgress: Boolean = true
    ) {

        Log.e(TAG, "loadImage $imageUrl")
        val context = imageView.context
        val parent = imageView.parent as ViewGroup
        var loadingBinding: LayoutLoadingDialogDefaultBinding? = null

        try {


            if (isProgress) {

                loadingBinding =
                    DataBindingUtil.inflate<LayoutLoadingDialogDefaultBinding>(
                        LayoutInflater.from(context),
                        R.layout.layout_loading_dialog_default,
                        parent,
                        false
                    ).apply {

                        root.also {
                            it.layoutDirection = if(LocaleHelperJava.isArabic(context)) View.LAYOUT_DIRECTION_RTL else View.LAYOUT_DIRECTION_LTR
                            it.layoutParams.width = imageView.layoutParams.width
                            it.layoutParams.height = imageView.layoutParams.height
                            it.setPadding(0 ,0 ,0 ,0)
                            it.id = imageView.id
                        }

                        contentLoading.also {
                            it.layoutParams.also {lp ->
                                if(imageView.layoutParams.width <= 120){
                                    lp.width = imageView.layoutParams.width
                                    lp.height = imageView.layoutParams.height
                                }
                            }
//
                        }
                    }


                val layoutParams: ViewGroup.LayoutParams

                //If Parent is ConstraintLayout
                if (parent is ConstraintLayout) {

                    val params = imageView.layoutParams as ConstraintLayout.LayoutParams

                    layoutParams = ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.WRAP_CONTENT,
                        ConstraintLayout.LayoutParams.WRAP_CONTENT
                    ).apply {

                        startToStart = params.startToStart
                        startToEnd = params.startToEnd
                        endToEnd = params.endToEnd
                        endToStart = params.endToStart

                        topToTop = params.topToTop
                        topToBottom = params.topToBottom
                        bottomToBottom = params.bottomToBottom
                        bottomToTop = params.bottomToTop

                        topMargin = params.topMargin

                        width = params.width
                        height = params.height
                    }

                } else
                    layoutParams = imageView.layoutParams

                loadingBinding.root.also {
                    it.layoutParams = layoutParams
                }


                if ( loadingBinding.root.parent != null) {
                    ( loadingBinding.root.parent as ViewGroup).removeView( loadingBinding.root) // <- fix
                }

                parent.addView(loadingBinding.root , layoutParams)
            }

            val glideCallback = object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {

                    Handler().post {

                        if(error != null ) {
                            imageView.setImageDrawable(error)
                            parent.removeView(loadingBinding?.root)
                        }

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
                        imageView.setImageDrawable(resource)
                        parent.removeView(loadingBinding?.root)
                    }
                    return true
                }
            }

            val options = RequestOptions()
//                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .placeholder(placeholder)

            Glide.with(context)
                .load(imageUrl)
                .apply(options)
                .listener(glideCallback)
                .into(imageView)

        }catch (ex : java.lang.Exception){
            ex.printStackTrace()
            if (error != null) {
                imageView.setImageDrawable(error)
                parent.removeView(loadingBinding?.root)
            }
        }


    }



    open fun loadImageUri(
        imageView: ImageView,
        imageUri: Uri?,
        error: Drawable? = null,
        placeholder: Drawable? = null,
        isProgress: Boolean = true
    ) {

        Log.e(TAG, "loadImage $imageUri")
        val context = imageView.context
        val parent = imageView.parent as ViewGroup
        var loadingBinding: LayoutLoadingDialogDefaultBinding? = null

        try {


            if (isProgress) {

                loadingBinding =
                    DataBindingUtil.inflate<LayoutLoadingDialogDefaultBinding>(
                        LayoutInflater.from(context),
                        R.layout.layout_loading_dialog_default,
                        parent,
                        false
                    ).apply {

                        root.also {
                            it.layoutDirection = if(LocaleHelperJava.isArabic(context)) View.LAYOUT_DIRECTION_RTL else View.LAYOUT_DIRECTION_LTR
                            it.layoutParams.width = imageView.layoutParams.width
                            it.layoutParams.height = imageView.layoutParams.height
                            it.setPadding(0 ,0 ,0 ,0)
                            it.id = imageView.id
                        }

                        contentLoading.also {
                            it.layoutParams.also {lp ->
                                if(imageView.layoutParams.width <= 120){
                                    lp.width = imageView.layoutParams.width
                                    lp.height = imageView.layoutParams.height
                                }
                            }
//
                        }
                    }


                val layoutParams: ViewGroup.LayoutParams

                //If Parent is ConstraintLayout
                if (parent is ConstraintLayout) {

                    val params = imageView.layoutParams as ConstraintLayout.LayoutParams

                    layoutParams = ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.WRAP_CONTENT,
                        ConstraintLayout.LayoutParams.WRAP_CONTENT
                    ).apply {

                        startToStart = params.startToStart
                        startToEnd = params.startToEnd
                        endToEnd = params.endToEnd
                        endToStart = params.endToStart

                        topToTop = params.topToTop
                        topToBottom = params.topToBottom
                        bottomToBottom = params.bottomToBottom
                        bottomToTop = params.bottomToTop

                        topMargin = params.topMargin

                        width = params.width
                        height = params.height
                    }

                } else
                    layoutParams = imageView.layoutParams

                loadingBinding.root.also {
                    it.layoutParams = layoutParams
                }


                if ( loadingBinding.root.parent != null) {
                    ( loadingBinding.root.parent as ViewGroup).removeView( loadingBinding.root) // <- fix
                }

                parent.addView(loadingBinding.root , layoutParams)
            }

            val glideCallback = object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {

                    Handler().post {

                        if(error != null ) {
                            imageView.setImageDrawable(error)
                            parent.removeView(loadingBinding?.root)
                        }

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
                        imageView.setImageDrawable(resource)
                        parent.removeView(loadingBinding?.root)
                    }
                    return true
                }
            }

            val options = RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .placeholder(placeholder)

            Glide.with(context)
                .load(imageUri)
                .apply(options)
                .listener(glideCallback)
                .into(imageView)

        }catch (ex : java.lang.Exception){
            ex.printStackTrace()
            if (error != null) {
                imageView.setImageDrawable(error)
                parent.removeView(loadingBinding?.root)
            }
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
