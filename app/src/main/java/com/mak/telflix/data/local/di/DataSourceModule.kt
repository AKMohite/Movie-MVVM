package com.mak.telflix.data.local.di

import com.mak.telflix.data.local.ITvDataSource
import com.mak.telflix.data.local.TvDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindLocalDataSource(source: TvDataSource): ITvDataSource

}