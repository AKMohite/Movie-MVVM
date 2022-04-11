package com.mak.telflix.domain

import com.mak.telflix.data.remote.dto.TvDTO
import com.mak.telflix.domain.common.ResultWrapper
import com.mak.telflix.domain.common.usecases.BaseUseCase
import com.mak.telflix.domain.interactors.home.PopularTVParams
import kotlinx.coroutines.flow.Flow

typealias RefreshableTVUseCase = BaseUseCase<PopularTVParams, ResultWrapper<List<TvDTO>>>