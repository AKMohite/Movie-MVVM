package com.mak.telflix.data.local.dao

import androidx.room.*
import com.mak.telflix.data.local.entity.BaseEntity

@Dao
interface BaseDAO<T: BaseEntity> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(vararg entity: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(entities: List<T>)

    @Update
    suspend fun update(entity: T): Int

    @Delete
    suspend fun delete(entity: T): Int
}