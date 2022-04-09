package com.mak.telflix.data.remote

import com.mak.telflix.data.remote.dto.PopularTvDTO
import com.mak.telflix.data.remote.dto.SuccessResponse
import com.mak.telflix.domain.util.TFConstants.API_GET_POPULAR_TV
import com.mak.telflix.domain.util.TFConstants.QUERY_LANGUAGE
import com.mak.telflix.domain.util.TFConstants.QUERY_PAGE
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBAPIService {

    @GET(API_GET_POPULAR_TV)
    fun getPopularTvSeries(
        @Query(QUERY_LANGUAGE) language: String = "en-US",
        @Query(QUERY_PAGE) page: Int,
    ): Single<SuccessResponse<PopularTvDTO>>
}