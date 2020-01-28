package com.mte.baseinfrastructure.network

import com.mte.infrastructurebase.data.source.remote.APIConfig
import com.mte.infrastructurebase.data.source.remote.ErrorHandler
import java.util.HashMap

/**
 * Created by Your name on 1/9/2020.
 */
class ShortageAPIConstant : APIConfig() {


    companion object{
        const val HOST = ApiConstants.HOST
        const val BASE_URL = ApiConstants.BASE_URL
    }

    override fun <T> getApiService(): Class<T> {
        return ApiService::class.java as Class<T>
    }

    override fun getBaseUrl(): String {
        return Companion.BASE_URL
    }

    override fun getHeaders(): HashMap<String, String>? {
        return  HashMap<String, String>()
                .apply {
                    put("Accept", "application/json")
                    put("Content-Type", "application/json")
//                    put("Authorization", "Bearer ${ShortageApp.user?.token}")
                }
    }

    override fun getErrorHandler(): ErrorHandler? {
        return null
    }

    override fun getHost(): String {
        return Companion.HOST
    }}