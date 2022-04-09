package com.mak.telflix.di

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
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    @Named("LoggingInterceptor")
    fun provideHttpLoggingInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
    }

    @Singleton
    @Provides
    @Named("ErrorInterceptor")
    fun provideErrorInterceptor(): Interceptor = ErrorInterceptor()

    @Singleton
    @Provides
    fun provideCallFactory(
        @Named("LoggingInterceptor") loggingInterceptor: Interceptor,
        @Named("ErrorInterceptor") errorInterceptor: Interceptor
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
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(client)

    @Singleton
    @Provides
    fun provideAPIService(retrofit: Retrofit.Builder): TMDBAPIService = retrofit
        .build()
        .create(TMDBAPIService::class.java)

}