package com.mak.telflix.domain.interactors.home

import com.mak.telflix.data.remote.IRemoteDataSource
import com.mak.telflix.data.remote.dto.PopularTvDTO
import com.mak.telflix.domain.common.usecases.BaseUseCase
import com.mak.telflix.domain.util.TFConstants.DEFAULT_LANGUAGE
import com.mak.telflix.domain.util.TFConstants.DEFAULT_PAGE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RefreshPopularTV @Inject constructor(
    private val remoteSource: IRemoteDataSource
): BaseUseCase<PopularTVParams, Flow<PopularTvDTO>>() {

    override fun doWork(params: PopularTVParams): Flow<PopularTvDTO> = flow {
        emit(remoteSource.getPopularTvSeries(params.language, params.page))
    }
}

data class PopularTVParams(
    val language: String = DEFAULT_LANGUAGE,
    val page: Int = DEFAULT_PAGE,
)