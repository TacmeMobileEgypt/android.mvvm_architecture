package com.mte.infrastructurebase.databinding

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.hamdy.infrastructurebase.utils.UtilsImageLoader


object ImageLoader{

    @JvmStatic
    @BindingAdapter("imageUri")
     fun loadImageUri(view: ImageView, uri: Uri?) {
        UtilsImageLoader.loadImageUri(view , uri)
    }

    @JvmStatic
    @BindingAdapter("imageUrl" , "error" , "placeholder" , "isProgress" )
     fun loadImageUrl(view: ImageView, url: String? , error : Drawable? = null ,  placeholder : Drawable? = null,  isProgress : Boolean = true) {
        UtilsImageLoader.loadImageUrl(view ,url , error , placeholder , isProgress)
    }

    @JvmStatic
    @BindingAdapter("imageDrawable")
     fun loadImageDrawable(view: ImageView,  @DrawableRes drawableId: Int) {
        UtilsImageLoader.loadDrawable(view ,drawableId)
    }


}
