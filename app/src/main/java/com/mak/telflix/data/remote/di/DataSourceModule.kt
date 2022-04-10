package com.mak.telflix.data.remote.di

import com.mak.telflix.data.remote.IRemoteDataSource
import com.mak.telflix.data.remote.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindRemoteDataSource(source: RemoteDataSource): IRemoteDataSource
}