package com.mak.telflix.domain.common

import com.mak.telflix.data.remote.dto.TvDTO
import com.mak.telflix.domain.DomainTV
import javax.inject.Inject

class TVMapper @Inject constructor() {

    fun mapToDomainResults(results: List<TvDTO>): List<DomainTV> {
        return results.map(::mapToDomainResult)
    }

    private fun mapToDomainResult(dto: TvDTO): DomainTV {
        return DomainTV(
            id = dto.id,
            name = dto.name,
            backdropURL = dto.backdropPath,
            posterURL = dto.posterPath
        )
    }
}