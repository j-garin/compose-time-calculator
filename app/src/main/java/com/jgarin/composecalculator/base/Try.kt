package com.jgarin.composecalculator.base

sealed class Try<out D> {

    data class Error(val error: Throwable) : Try<Nothing>()

    data class Success<out D>(val data: D) : Try<D>()

    fun doOnSuccess(block: (D) -> Unit): Try<D> {
        if (this is Success) block(data)
        return this
    }

    fun doOnError(block: (Throwable) -> Unit): Try<D> {
        if (this is Error) block(error)
        return this
    }

    companion object {
        inline operator fun <T> invoke(block: () -> T): Try<T> = try {
            Success(block())
        } catch (e: Throwable) {
            Error(e)
        }
    }
}

suspend inline fun <I, O> Try<I>.map(mapper: (I) -> O): Try<O> =
    when (this) {
        is Try.Error -> Try.Error(this.error)
        is Try.Success -> Try { mapper(this.data) }
    }

suspend inline fun <I, O> Try<I>.flatMap(mapper: (I) -> Try<O>): Try<O> =
    when (this) {
        is Try.Error -> Try.Error(this.error)
        is Try.Success -> mapper(this.data)
    }

