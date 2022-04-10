package com.mak.telflix.domain.common.usecases

abstract class BaseUseCase<I, O> {
    suspend operator fun invoke(params: I): O {
        return doWork(params)
    }

    abstract suspend fun doWork(params: I): O
}
