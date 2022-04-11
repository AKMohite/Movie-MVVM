package com.mak.telflix.feature.home

import com.mak.telflix.data.remote.dto.TvDTO

data class HomeState(
    val isLoading: Boolean = false,
    val items: List<TvDTO> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 1
)