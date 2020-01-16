package com.mte.infrastructurebase.utils

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

object MultipartUtils {

    fun createRequestBody(key: String?): RequestBody {
       return RequestBody.create(MediaType.parse("text/plain"), key ?: "")
    }

    fun createFilePart(key: String, file: File?): MultipartBody.Part?{

        if(file == null) return null

       return  MultipartBody.Part.createFormData(
           key,
            file?.name,
            RequestBody.create(MediaType.parse("*/*"), file)
        )
    }
}