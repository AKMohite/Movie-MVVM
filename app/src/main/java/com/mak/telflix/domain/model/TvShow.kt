package com.mak.telflix.domain.model

data class TvShow(
    val id: Int,
    val name: String,
    val backdropURL: String?,
    val posterURL: String,
)