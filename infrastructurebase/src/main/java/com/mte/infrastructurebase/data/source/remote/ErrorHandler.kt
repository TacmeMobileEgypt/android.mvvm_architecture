package com.mte.infrastructurebase.data.source.remote

import android.annotation.SuppressLint
import android.util.Log
import com.google.gson.stream.MalformedJsonException
import com.mte.infrastructurebase.App
import com.mte.infrastructurebase.R
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by Your name on 1/28/2020.
 */
interface ErrorHandler {
    fun getErrorFromBody(errorBody: String?): String?


    @SuppressLint("LogNotTimber")
    fun getHttpExceptionError(error: Throwable): String?{

        Log.e("ErrorHandler" , error.message)

        val context = App.appInstance.applicationContext

        return if (error is SocketTimeoutException) {
            context.getString(R.string.requestTimeOutError)
        } else if (error is MalformedJsonException) {
            context.getString(R.string.responseMalformedJson)
        } else if (error is UnknownHostException) {
            context.getString(R.string.unknownError)
        } else if (error is ConnectException) {
            context.getString(R.string.networkError)
        } else if (error is IOException) {
            context.getString(R.string.networkError)
        } else if (error is HttpException) {
            context.getString(R.string.unknownError)
        } else {
            context.getString(R.string.unknownError)
        }
    }
}