package com.mak.telflix.data.remote.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LoggingIOInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ErrorIOInterceptor