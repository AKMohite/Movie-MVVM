package com.mak.telflix.domain.interactors.home

import com.mak.telflix.data.remote.IRemoteDataSource
import com.mak.telflix.data.remote.dto.TvDTO
import com.mak.telflix.domain.RefreshableTVUseCase
import com.mak.telflix.domain.common.ResultWrapper
import com.mak.telflix.domain.util.TFConstants.DEFAULT_LANGUAGE
import com.mak.telflix.domain.util.TFConstants.DEFAULT_PAGE
import javax.inject.Inject

class RefreshPopularTV @Inject constructor(
    private val remoteSource: IRemoteDataSource
): RefreshableTVUseCase() {

    override suspend fun doWork(params: PopularTVParams): ResultWrapper<List<TvDTO>> {
        return try {
            ResultWrapper.Success(remoteSource.getPopularTvSeries(params.language, params.page).results)
        } catch (t: Throwable) {
            ResultWrapper.Error(t)
        }
    }
}

data class PopularTVParams(
    val language: String = DEFAULT_LANGUAGE,
    val page: Int = DEFAULT_PAGE,
)