package com.mte.infrastructurebase.data.source.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Suppress("NAME_SHADOWING")
abstract class ApiServiceFactory {


    companion object {

        var errorHandler : ErrorHandler ? = null


        fun <T> getService(apiConfig: APIConfig): T {

            errorHandler = apiConfig.getErrorHandler()

            return synchronized(this) {
                val instance = Retrofit.Builder()
                    .baseUrl(apiConfig.getHost())
                    .client(provideOkHttpClient(apiConfig))
//                    .addConverterFactory(MoshiConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(LiveDataCallAdapterFactory())
                    .build()
                    .create(apiConfig.getApiService<T>())
                instance
            }
        }

        private fun provideOkHttpClient(apiConfig : APIConfig? = null): OkHttpClient {

            val client = OkHttpClient.Builder()
            client.addInterceptor(initializeHeaders(apiConfig))//Todo()
            client.readTimeout(60, TimeUnit.SECONDS);
            client.connectTimeout(60, TimeUnit.SECONDS);
//            if(BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                client.addInterceptor(interceptor)//Add Loggong Intercepter
//            }

            return client.build()
        }



        private fun initializeHeaders(apiConfig : APIConfig? = null): Interceptor {

//            val headers = HashMap<String, String>()
////            token?.let {
////                headers.put("Authorization", "Bearer ${it}")
////            }
////            headers.put("Accept", "application/json")
//            headers.put("Content-Type", "application/json")
////            headers.put("Content-Type", "application/x-www-form-urlencoded")
//            headers.put("X-Requested-With", "XMLHttpRequest")
//            headers.put("Authorization", "Bearer $token")

            return Interceptor { chain ->
                val original = chain.request()
                val request: Request
                val builder = original.newBuilder()
                val headers = apiConfig?.getHeaders()
                headers?.forEach {
                    builder.header(it.key, it.value)
                }
                builder.method(original.method(), original.body())
                request = builder.build()
                chain.proceed(request)
            }

        }
    }
}