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
//    db tables
    const val TABLE_TV = "tv_series"
    const val TABLE_POPULAR_TV = "popular_tv_series"
//    table columns
    const val C_ID = "id"
    const val C_SHOW_ID = "show_id"
    const val C_PAGE_NO = "page_no"
    const val C_BACKDROP_URL = "backdrop_path"
    const val C_FIRST_AIR_DATE = "first_air_date"
    const val C_GENRE_IDS = "genre_ids"
    const val C_NAME = "name"
    const val C_ORIGIN_COUNTRY = "origin_country"
    const val C_ORIGINAL_LANGUAGE = "original_language"
    const val C_ORIGINAL_NAME = "original_name"
    const val C_OVERVIEW = "overview"
    const val C_POPULARITY = "popularity"
    const val C_POSTER_PATH = "poster_path"
    const val C_VOTE_AVG = "vote_average"
    const val C_VOTE_COUNT = "vote_count"

//    domain
    const val DEFAULT_LANGUAGE = "en-US"
    const val DEFAULT_PAGE = 1
    const val DEFAULT_PAGE_SIZE = 20
}