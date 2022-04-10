package com.mak.telflix.data.remote.di

import com.mak.telflix.BuildConfig
import com.mak.telflix.data.remote.ErrorInterceptor
import com.mak.telflix.data.remote.TMDBAPIService
import com.mak.telflix.domain.util.TFConstants.BASE_URL
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    @LoggingIOInterceptor
    fun provideHttpLoggingInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
    }

    @Singleton
    @Provides
    @ErrorIOInterceptor
    fun provideErrorInterceptor(): Interceptor = ErrorInterceptor()

    @Singleton
    @Provides
    fun provideCallFactory(
        @LoggingIOInterceptor loggingInterceptor: Interceptor,
        @ErrorIOInterceptor errorInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(errorInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Singleton
    @Provides
    fun provideMoshiConverter(
        moshi: Moshi
    ): MoshiConverterFactory {
        return MoshiConverterFactory.create(moshi)
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        moshiConverterFactory: MoshiConverterFactory,
        client: OkHttpClient
    ): Retrofit.Builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(moshiConverterFactory)
        .client(client)

    @Singleton
    @Provides
    fun provideAPIService(retrofit: Retrofit.Builder): TMDBAPIService = retrofit
        .build()
        .create(TMDBAPIService::class.java)

}