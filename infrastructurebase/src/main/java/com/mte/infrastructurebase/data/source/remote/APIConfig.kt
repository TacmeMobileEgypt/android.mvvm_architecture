package com.mte.infrastructurebase.data.source.remote

import java.util.HashMap


abstract class APIConfig {

     val HOST = "http://shortage.revival.sa"
     val BASE_URL = "$HOST/privatedashboard/api"

    abstract fun getHost() : String
    abstract fun getBaseUrl() : String
    abstract fun <T> getApiService() :  Class<T>
    abstract fun getHeaders(): HashMap<String, String>?

}