package com.jgarin.composecalculator.base

import kotlinx.coroutines.*

abstract class BaseUseCase<in Params, out Type> {

    protected val backgroundThread = CoroutineScope(Dispatchers.IO)
    private val mainThread = Dispatchers.Main

    suspend operator fun invoke(params: Params): Try<Type> = run(params)

    abstract suspend fun run(params: Params): Try<Type>
}