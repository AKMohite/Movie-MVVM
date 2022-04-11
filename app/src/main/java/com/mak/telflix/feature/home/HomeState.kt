package com.mak.telflix.feature.home

import com.mak.telflix.domain.DomainTV

data class HomeState(
    val isLoading: Boolean = false,
    val items: List<DomainTV> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 1
)