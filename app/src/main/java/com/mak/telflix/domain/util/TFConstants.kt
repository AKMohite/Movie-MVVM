package com.mak.telflix.domain.util

object TFConstants {

//    data:remote
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_GET_POPULAR_TV = "tv/popular"
    const val API_GET_CURRENTLY_ON_AIR_TV = "tv/on_the_air"
    const val API_GET_AIRING_TODAY_TV = "tv/airing_today"
    const val QUERY_LANGUAGE = "language"
    const val QUERY_PAGE = "page"

//    data:local

//    domain
    const val DEFAULT_LANGUAGE = "en-US"
    const val DEFAULT_PAGE = 1
    const val DEFAULT_PAGE_SIZE = 20
}