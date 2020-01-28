package com.mte.infrastructurebase.data.source.remote


import android.annotation.SuppressLint
import android.util.Log
import com.google.gson.Gson
import com.google.gson.stream.MalformedJsonException
import com.mte.infrastructurebase.App
import com.mte.infrastructurebase.R
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.regex.Pattern
import kotlin.String as String1

 sealed class ApiResponse<T> {

    companion object {

        private val TAG: kotlin.String = "ApiResponse"

        @SuppressLint("LogNotTimber")
        fun <T : BaseResponseModel?> create(error: Throwable): ApiErrorResponse<T> {
            Log.e("ApiResponse", error.message)
            return ApiErrorResponse(getCustomErrorMessage(error))
        }

        fun <T : BaseResponseModel> create(response: Response<T>): ApiResponse<T> {

            return if (response.isSuccessful) {

                val body = response.body()

                if (body is BaseResponseModel && body.getSuccess() == null) {
                    ApiErrorResponse(body.getError() ?: getCustomErrorMessage(Throwable(body.getError())))
                } else if (body == null || response.code() == 204) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(
                        body = body,
                        linkHeader = response.headers()?.get("link")
                    )
                }

            } else {

                val errorBody = response.errorBody()?.string()

                val errorFromBody = ApiServiceFactory.errorHandler?.getErrorFromBody(errorBody)

                val errorMsg = if(errorBody == null) response.message() else errorFromBody

                ApiErrorResponse(errorMsg ?: getCustomErrorMessage(Throwable(errorMsg)))
            }
        }

        @SuppressLint("LogNotTimber")
        fun getCustomErrorMessage(error: Throwable): kotlin.String {

            val context = App.appInstance.applicationContext
            Log.e(TAG, "getCustomErrorMessage ${error.message}")

            return if (error is SocketTimeoutException) {
                context.getString(R.string.requestTimeOutError)
            } else if (error is MalformedJsonException) {
                context.getString(R.string.responseMalformedJson)
            } else if (error is UnknownHostException) {
                context.getString(R.string.unknownError)
            } else if (error is IOException) {
                context.getString(R.string.networkError)
            } else if (error is HttpException) {
                error.response().message()
            } else {
                context.getString(R.string.unknownError)
            }
        }
    }
}

/**
 * separate class for HTTP 204 responses so that we can make ApiSuccessResponse's body non-null.
 */
class ApiEmptyResponse<T : BaseResponseModel> : ApiResponse<T>()

data class ApiSuccessResponse<T : BaseResponseModel?>(
    val body: T,
    val links: Map<String1, String1>
) : ApiResponse<T>() {
    constructor(body: T, linkHeader: String1?) : this(
        body = body,
        links = linkHeader?.extractLinks() ?: emptyMap()
    )

    val nextPage: Int? by lazy(LazyThreadSafetyMode.NONE) {
        links[NEXT_LINK]?.let { next ->
            val matcher = PAGE_PATTERN.matcher(next)
            if (!matcher.find() || matcher.groupCount() != 1) {
                null
            } else {
                try {
                    Integer.parseInt(matcher.group(1))
                } catch (ex: NumberFormatException) {
                    null
                }
            }
        }
    }

    companion object {
        private val LINK_PATTERN = Pattern.compile("<([^>]*)>[\\s]*;[\\s]*rel=\"([a-zA-Z0-9]+)\"")
        private val PAGE_PATTERN = Pattern.compile("\\bpage=(\\d+)")
        private const val NEXT_LINK = "next"

        private fun String1.extractLinks(): Map<String1, String1> {
            val links = mutableMapOf<String1, String1>()
            val matcher = LINK_PATTERN.matcher(this)

            while (matcher.find()) {
                val count = matcher.groupCount()
                if (count == 2) {
                    links[matcher.group(2)] = matcher.group(1)
                }
            }
            return links
        }

    }
}

data class ApiErrorResponse<T : BaseResponseModel?>(val errorMessage: String1) : ApiResponse<T>()