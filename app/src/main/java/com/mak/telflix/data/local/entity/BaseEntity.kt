package com.mak.telflix.data.local.entity

interface BaseEntity {
    val id: Long
}

interface Entry : BaseEntity {
    val showId: Long
}

interface MultipleEntry : Entry {
    val otherShowId: Long
}

interface PaginatedEntry : Entry {
    val page: Int
}