package com.mak.telflix.domain.common.usecases

abstract class BaseUseCase<I, O>: ITVUseCase<I, O> {
    suspend operator fun invoke(params: I): O {
        return doWork(params)
    }
}

interface ITVUseCase<I, O>{
    suspend fun doWork(params: I): O
}
