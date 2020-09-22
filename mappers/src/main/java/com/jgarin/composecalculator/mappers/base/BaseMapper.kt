package com.jgarin.composecalculator.mappers.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseMapper<in I, out O> {

    suspend operator fun invoke(input: I): O = withContext(Dispatchers.Default) {
        map(input)
    }

    protected abstract fun map(input: I): O
}