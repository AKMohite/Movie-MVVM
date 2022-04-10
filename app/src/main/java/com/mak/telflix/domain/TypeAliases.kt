package com.mak.telflix.domain

import com.mak.telflix.data.remote.dto.PopularTvDTO
import com.mak.telflix.domain.common.usecases.BaseUseCase
import com.mak.telflix.domain.interactors.home.PopularTVParams
import kotlinx.coroutines.flow.Flow

typealias RefreshableTVUseCase = BaseUseCase<PopularTVParams, Flow<PopularTvDTO>>