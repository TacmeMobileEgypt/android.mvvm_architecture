package com.mte.infrastructurebase.data.source.remote

/**
 * Created by Your name on 1/28/2020.
 */
interface ErrorHandler {
    fun getErrorFromBody(errorBody: String?): String?
}