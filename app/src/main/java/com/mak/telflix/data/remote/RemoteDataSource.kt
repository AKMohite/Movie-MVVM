package com.mak.telflix.data.remote

import com.mak.telflix.data.remote.dto.PopularTvDTO
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: TMDBAPIService
): IRemoteDataSource {

    override suspend fun getPopularTvSeries(language: String, page: Int): PopularTvDTO {
        return api.getPopularTvSeries(language, page)
    }
}

interface IRemoteDataSource{
    suspend fun getPopularTvSeries(
        language: String,
        page: Int
    ): PopularTvDTO
}