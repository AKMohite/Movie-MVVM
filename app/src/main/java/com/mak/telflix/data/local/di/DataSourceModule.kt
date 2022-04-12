package com.mak.telflix.data.local.di

import com.mak.telflix.data.local.ILocalDataSource
import com.mak.telflix.data.local.LocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindLocalDataSource(source: LocalDataSource): ILocalDataSource

}