package com.mak.telflix.domain.common.usecases

abstract class BaseUseCase<I, O> {
    operator fun invoke(params: I): O {
        return doWork(params)
    }

    abstract fun doWork(params: I): O
}
