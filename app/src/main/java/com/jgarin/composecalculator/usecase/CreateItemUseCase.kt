package com.jgarin.composecalculator.usecase

import com.jgarin.composecalculator.base.BaseUseCase
import com.jgarin.composecalculator.base.Try
import com.jgarin.composecalculator.repository.DataRepository

class CreateItemUseCase(private val repo: DataRepository) :
    BaseUseCase<CreateItemUseCase.Params, Unit>() {

    data class Params(val hours: Int, val minutes: Int)

    override suspend fun run(params: Params): Try<Unit> {
        return Try { repo.creteItem(hours = params.hours, minutes = params.minutes) }
    }
}