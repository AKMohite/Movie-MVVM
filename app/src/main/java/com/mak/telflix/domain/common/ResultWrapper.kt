package com.mak.telflix.domain.common

sealed class ResultWrapper<out R> {
    data class Success<out T>(val data: T) : ResultWrapper<T>()
    data class Error(val throwable: Throwable) : ResultWrapper<Nothing>()
}
