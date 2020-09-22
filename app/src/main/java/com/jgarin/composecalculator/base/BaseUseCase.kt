package com.jgarin.composecalculator.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseUseCase<in Params, out Type> {

    suspend operator fun invoke(params: Params): Try<Type> = withContext(Dispatchers.Default) {
        run(params)
    }

    protected abstract suspend fun run(params: Params): Try<Type>
}