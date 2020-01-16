package com.mte.infrastructurebase.attachments

import android.net.Uri

interface OnTakeFilePhotoListener {
    fun OnTakePhoto(image: Uri?)
}