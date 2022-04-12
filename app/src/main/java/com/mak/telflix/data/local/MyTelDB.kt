package com.mak.telflix.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mak.telflix.data.local.dao.TvDAO
import com.mak.telflix.data.local.entity.TvEntity

@Database(
    entities = [TvEntity::class],
    version = 1
)
abstract class MyTelDB: RoomDatabase(), TvDB

interface TvDB {
    fun tvDao(): TvDAO
}
