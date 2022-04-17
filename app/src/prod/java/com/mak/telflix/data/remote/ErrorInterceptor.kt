package com.mak.telflix.data.remote

import com.mak.telflix.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

private const val API_KEY = BuildConfig.TMDBAPIKey
class ErrorInterceptor: Interceptor {

    @Throws(Throwable::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
//            if (!context.isOnline()) throw NoNetworkException()
            val request = chain.request()
            val url = request.url.newBuilder().addQueryParameter("api_key", API_KEY).build()
            val builder = request.newBuilder().url(url)
//                .header(HEADER_ACCEPT, HEADER_APPLICATION_JSON)
//                .header(HEADER_CONTENT_TYPE, HEADER_APPLICATION_JSON)
            val response = chain.proceed(builder.build())

            /*if (!response.isSuccessful) {
                val responseCode = response.code
                val apiStatusThrowable = handleAPIStatusCodes(responseCode)
                crashlyticsLog.exceptionTag("$API_STATUS- $responseCode",apiStatusThrowable)
                throw apiStatusThrowable
            }*/
            return response

        } catch (e: Throwable) {
//            crashlyticsLog.exception(e)
//            throw throwableException(e)
            throw e
        }
    }
}