package com.hamdy.infrastructurebase.attachments

import android.graphics.Bitmap
import android.net.Uri

interface OnTakeFilePhotoListener {
    fun OnTakePhoto(image: Uri?)
}