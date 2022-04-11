package com.mak.telflix.data.remote

import com.mak.telflix.data.remote.dto.BaseTMDBDTO
import com.mak.telflix.domain.util.TFConstants.API_GET_AIRING_TODAY_TV
import com.mak.telflix.domain.util.TFConstants.API_GET_CURRENTLY_ON_AIR_TV
import com.mak.telflix.domain.util.TFConstants.API_GET_POPULAR_TV
import com.mak.telflix.domain.util.TFConstants.QUERY_LANGUAGE
import com.mak.telflix.domain.util.TFConstants.QUERY_PAGE
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBAPIService {

    /**
     * API returns list of the current popular TV shows on TMDB. This list updates daily.
     * */
    @GET(API_GET_POPULAR_TV)
    suspend fun getPopularTvSeries(
        @Query(QUERY_LANGUAGE) language: String,
        @Query(QUERY_PAGE) page: Int,
    ): BaseTMDBDTO

    /**
     * This API returns any TV show that has an episode with an air date in the next 7 days.
     * */
    @GET(API_GET_CURRENTLY_ON_AIR_TV)
    suspend fun getCurrentlyOnAirTvSeries(
        @Query(QUERY_LANGUAGE) language: String,
        @Query(QUERY_PAGE) page: Int,
    ): BaseTMDBDTO

    /**
     * This API returns any TV show that has an episode with an air date in the next 7 days.
     * */
    @GET(API_GET_AIRING_TODAY_TV)
    suspend fun getSeriesAiringToday(
        @Query(QUERY_LANGUAGE) language: String,
        @Query(QUERY_PAGE) page: Int,
    ): BaseTMDBDTO

}