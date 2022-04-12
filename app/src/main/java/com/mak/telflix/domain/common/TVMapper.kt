package com.mak.telflix.domain.common

import com.mak.telflix.data.local.entity.TvEntity
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

    fun mapToEntities(results: List<TvDTO>, page: Int): List<TvEntity> {
        return results.map{ dto ->
            mapToEntity(dto, page)
        }
    }

    private fun mapToEntity(dto: TvDTO, page: Int): TvEntity {
        return TvEntity(
            id = dto.id,
            page = page,
            backdropPath = dto.backdropPath,
            firstAirDate = dto.firstAirDate,
            genreIds = dto.genreIds?.joinToString(","),
            name = dto.name,
            originCountry = if (!dto.originCountry.isNullOrEmpty()) dto.originCountry[0] else "",
            originalLanguage = dto.originalLanguage,
            originalName = dto.originalName,
            overview = dto.overview ?: "",
            popularity = dto.popularity,
            posterPath = dto.posterPath,
            voteAverage = dto.voteAverage,
            voteCount = dto.voteCount
        )
    }
}