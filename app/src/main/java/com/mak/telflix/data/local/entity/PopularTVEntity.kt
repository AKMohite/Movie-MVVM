package com.mak.telflix.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.mak.telflix.domain.util.TFConstants.C_ID
import com.mak.telflix.domain.util.TFConstants.C_PAGE_NO
import com.mak.telflix.domain.util.TFConstants.C_SHOW_ID
import com.mak.telflix.domain.util.TFConstants.TABLE_POPULAR_TV

@Entity(
    tableName = TABLE_POPULAR_TV,
    indices = [
        Index(
            value = [C_SHOW_ID], unique = true
        )
    ],
    foreignKeys = [
        ForeignKey(
            entity = TvEntity::class,
            parentColumns = arrayOf(C_ID),
            childColumns = arrayOf(C_SHOW_ID),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PopularTVEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = C_ID)
    override val id: Long,
    @ColumnInfo(name = C_SHOW_ID)
    override val showId: Long,
    @ColumnInfo(name = C_PAGE_NO)
    override val page: Int,
): PaginatedEntry