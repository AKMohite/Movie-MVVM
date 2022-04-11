package com.mak.telflix.domain.util

import com.mak.telflix.BuildConfig

object TFConstants {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = BuildConfig.TMDBAPIKey

    const val API_GET_POPULAR_TV = "tv/popular"
    const val QUERY_LANGUAGE = "language"
    const val QUERY_PAGE = "page"
}