package com.mak.telflix.data.local

import com.mak.telflix.data.local.dao.TvDAO
import com.mak.telflix.data.local.entity.TvEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvDataSource @Inject constructor(
    private val tvDAO: TvDAO
): ITvDataSource {

    override suspend fun insertTv(entities: List<TvEntity>) {
        tvDAO.insertAll(entities)
    }

    override suspend fun deleteAllTvEntries() {
        tvDAO.deleteAll()
    }

    override suspend fun getTvSeries(): Flow<List<TvEntity>> = tvDAO.getTvSeries()

}

interface ITvDataSource {
    suspend fun insertTv(entities: List<TvEntity>)
    suspend fun deleteAllTvEntries()
    suspend fun getTvSeries(): Flow<List<TvEntity>>
}