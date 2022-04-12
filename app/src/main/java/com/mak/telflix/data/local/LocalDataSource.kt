package com.mak.telflix.data.local

import com.mak.telflix.data.local.entity.TvEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val db: MyTelDB
): ILocalDataSource {
    private val tvDAO = db.tvDao()

    override suspend fun insert(entities: List<TvEntity>) {
        tvDAO.insertAll(entities)
    }

    override suspend fun deleteAllEntries() {
        TODO("Not yet implemented")
    }

    override suspend fun getTvSeries(page: Int): Flow<List<TvEntity>> = tvDAO.getTvSeries(page)

}

interface ILocalDataSource {
    suspend fun insert(entities: List<TvEntity>)
    suspend fun deleteAllEntries()
    suspend fun getTvSeries(page: Int): Flow<List<TvEntity>>
}