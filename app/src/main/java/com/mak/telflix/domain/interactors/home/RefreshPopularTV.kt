package com.mak.telflix.domain.interactors.home

import com.mak.telflix.data.remote.IRemoteDataSource
import com.mak.telflix.domain.DomainTV
import com.mak.telflix.domain.RefreshableTVUseCase
import com.mak.telflix.domain.common.ResultWrapper
import com.mak.telflix.domain.common.TVMapper
import com.mak.telflix.domain.util.TFConstants.DEFAULT_LANGUAGE
import com.mak.telflix.domain.util.TFConstants.DEFAULT_PAGE
import javax.inject.Inject

class RefreshPopularTV @Inject constructor(
    private val remoteSource: IRemoteDataSource,
    private val mapper: TVMapper
): RefreshableTVUseCase {

    override suspend operator fun invoke(params: PopularTVParams): ResultWrapper<List<DomainTV>> {
        return try {
            val response = remoteSource.getPopularTvSeries(params.language, params.page)
            val domainTv = mapper.mapToDomainResults(response.results)
            ResultWrapper.Success(domainTv)
        } catch (t: Throwable) {
            ResultWrapper.Error(t)
        }
    }
}

data class PopularTVParams(
    val language: String = DEFAULT_LANGUAGE,
    val page: Int = DEFAULT_PAGE,
)