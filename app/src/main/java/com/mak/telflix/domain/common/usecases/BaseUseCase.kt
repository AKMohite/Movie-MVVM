package com.mak.telflix.domain.common.usecases

interface BaseUseCase<I, O> {
    suspend operator fun invoke(params: I): O
}

