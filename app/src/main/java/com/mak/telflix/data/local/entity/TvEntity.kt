package com.mak.telflix.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.mak.telflix.domain.util.TFConstants.C_BACKDROP_URL
import com.mak.telflix.domain.util.TFConstants.C_FIRST_AIR_DATE
import com.mak.telflix.domain.util.TFConstants.C_GENRE_IDS
import com.mak.telflix.domain.util.TFConstants.C_ID
import com.mak.telflix.domain.util.TFConstants.C_NAME
import com.mak.telflix.domain.util.TFConstants.C_ORIGINAL_LANGUAGE
import com.mak.telflix.domain.util.TFConstants.C_ORIGINAL_NAME
import com.mak.telflix.domain.util.TFConstants.C_ORIGIN_COUNTRY
import com.mak.telflix.domain.util.TFConstants.C_OVERVIEW
import com.mak.telflix.domain.util.TFConstants.C_PAGE_NO
import com.mak.telflix.domain.util.TFConstants.C_POPULARITY
import com.mak.telflix.domain.util.TFConstants.C_POSTER_PATH
import com.mak.telflix.domain.util.TFConstants.C_VOTE_AVG
import com.mak.telflix.domain.util.TFConstants.C_VOTE_COUNT
import com.mak.telflix.domain.util.TFConstants.TABLE_TV

@Entity(
    tableName = TABLE_TV
)
data class TvEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = C_ID)
    override val id: Long,
    @ColumnInfo(name = C_PAGE_NO)
    val page: Int,
    @ColumnInfo(name = C_BACKDROP_URL)
    val backdropPath: String?,
    @ColumnInfo(name = C_FIRST_AIR_DATE)
    val firstAirDate: String,
    @ColumnInfo(name = C_GENRE_IDS)
    val genreIds: String?,
    @ColumnInfo(name = C_NAME)
    val name: String,
    @ColumnInfo(name = C_ORIGIN_COUNTRY)
    val originCountry: String,
    @ColumnInfo(name = C_ORIGINAL_LANGUAGE)
    val originalLanguage: String,
    @ColumnInfo(name = C_ORIGINAL_NAME)
    val originalName: String?,
    @ColumnInfo(name = C_OVERVIEW)
    val overview: String,
    @ColumnInfo(name = C_POPULARITY)
    val popularity: Double,
    @ColumnInfo(name = C_POSTER_PATH)
    val posterPath: String,
    @ColumnInfo(name = C_VOTE_AVG)
    val voteAverage: Double,
    @ColumnInfo(name = C_VOTE_COUNT)
    val voteCount: Int
): BaseEntity {
    @Ignore
    val genres = genreIds?.split(",")?.mapNotNull { it.trim() } ?: emptyList()
}
