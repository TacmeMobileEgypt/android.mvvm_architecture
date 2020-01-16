package com.mte.infrastructurebase.databinding

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.mte.infrastructurebase.utils.UtilsImageLoader


object ImageLoader{

    @JvmStatic
    @BindingAdapter("imageUri")
     fun loadImageUri(view: ImageView, uri: Uri?) {
        UtilsImageLoader.loadImageUri(view , uri)
    }

    @JvmStatic
    @BindingAdapter("imageUrl" , "error")
     fun loadImageUrl(view: ImageView, url: String? , error : Drawable?) {
        UtilsImageLoader.loadImageUrl(view ,url , error )
    }

    @JvmStatic
    @BindingAdapter("imageDrawable")
     fun loadImageDrawable(view: ImageView,  @DrawableRes drawableId: Int) {
        UtilsImageLoader.loadDrawable(view ,drawableId)
    }


}
