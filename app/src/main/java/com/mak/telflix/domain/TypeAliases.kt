package com.mak.telflix.domain

import com.mak.telflix.domain.common.ResultWrapper
import com.mak.telflix.domain.common.usecases.BaseUseCase
import com.mak.telflix.domain.interactors.home.PopularTVParams
import com.mak.telflix.domain.model.TvShow

typealias RefreshableTVUseCase = BaseUseCase<PopularTVParams, ResultWrapper<List<DomainTV>>>

typealias DomainTV = TvShow