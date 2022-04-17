package com.mak.telflix.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.mak.telflix.data.local.entity.TvEntity
import com.mak.telflix.domain.util.TFConstants.C_PAGE_NO
import com.mak.telflix.domain.util.TFConstants.TABLE_TV
import kotlinx.coroutines.flow.Flow

@Dao
interface TvDAO: BaseDAO<TvEntity> {

    @Query("SELECT * FROM $TABLE_TV WHERE $C_PAGE_NO = :page")
    fun getTvSeries(page: Int): Flow<List<TvEntity>>

    @Query("DELETE FROM $TABLE_TV WHERE $C_PAGE_NO = :page")
    suspend fun deletePage(page: Int)

    @Transaction
    suspend fun updatePage(page: Int, entities: List<TvEntity>) {
        deletePage(page)
        insertAll(entities)
    }

}