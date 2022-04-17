package com.mak.telflix.data.local.di

import android.content.Context
import android.os.Debug
import androidx.room.Room
import com.mak.telflix.data.local.MyTelDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideTVDatabase(
        @ApplicationContext context: Context
    ): MyTelDB {
        val builder = Room.databaseBuilder(context, MyTelDB::class.java, "tv_series.db")
            .fallbackToDestructiveMigration()
        if (Debug.isDebuggerConnected()) {
            builder.allowMainThreadQueries()
        }

        return builder.build()
    }

}