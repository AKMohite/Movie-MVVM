package com.mak.telflix.data.remote

import com.mak.telflix.domain.util.TFConstants.API_GET_AIRING_TODAY_TV
import com.mak.telflix.domain.util.TFConstants.API_GET_CURRENTLY_ON_AIR_TV
import com.mak.telflix.domain.util.TFConstants.API_GET_POPULAR_TV
import com.mak.telflix.domain.util.TFConstants.BASE_URL
import com.mak.telflix.domain.util.TFConstants.QUERY_PAGE
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

private const val SUCCESS_STATUS_CODE = 200
class ErrorInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            val request = chain.request()
            val httpUrl = request.url
            val uri = httpUrl.toUri().toString()
            val responseString: String = when {
                uri.contains("$BASE_URL$API_GET_POPULAR_TV") -> {
                    val queryPage = httpUrl.queryParameter(QUERY_PAGE)
                    getResourceAsText("tmdb-api/popular-tv/success-$queryPage.json")
                }

                uri.contains("$BASE_URL$API_GET_CURRENTLY_ON_AIR_TV") -> {
                    val queryPage = httpUrl.queryParameter(QUERY_PAGE)
                    getResourceAsText("tmdb-api/on-air-tv/success-$queryPage.json")
                }

                uri.contains("$BASE_URL$API_GET_AIRING_TODAY_TV") -> {
                    val queryPage = httpUrl.queryParameter(QUERY_PAGE)
                    getResourceAsText("tmdb-api/today-airing-tv/success-$queryPage.json")
                }

                else -> throw IllegalAccessError(
                    """MockInterceptor is only meant for Testing Purposes 
                and bound to be used only with DEBUG mode
                """.trimIndent()
                )
            }
            return chain.proceed(chain.request())
                .newBuilder()
                .code(SUCCESS_STATUS_CODE)
                .protocol(Protocol.HTTP_2)
                .message(responseString)
                .body(
                    responseString.toByteArray()
                        .toResponseBody("application/json".toMediaTypeOrNull())
                )
                .addHeader("content-type", "application/json")
                .build()
        } catch (t: Throwable) {
            throw IllegalAccessError(
                """MockInterceptor is only meant for Testing Purposes 
                and bound to be used only with DEBUG mode
                """.trimIndent()
            )
        }
    }

    // get resources such as json from file
    private fun getResourceAsText(path: String): String {
        return object {}.javaClass.classLoader?.getResource(path)?.readText()
            ?: throw IllegalArgumentException("response not found for: $path")
    }
}