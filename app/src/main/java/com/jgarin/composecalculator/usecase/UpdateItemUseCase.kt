package com.jgarin.composecalculator.usecase

import com.jgarin.composecalculator.base.BaseUseCase
import com.jgarin.composecalculator.base.Try
import com.jgarin.composecalculator.data.DurationItem
import com.jgarin.composecalculator.repository.DataRepository

class UpdateItemUseCase(private val repo: DataRepository) :
    BaseUseCase<UpdateItemUseCase.Params, Unit>() {

    data class Params(val id: Long, val hours: Int, val minutes: Int)

    override suspend fun run(params: Params): Try<Unit> {
        return Try {
            repo.updateItem(
                DurationItem(
                    id = params.id,
                    hours = params.hours,
                    minutes = params.minutes,
                )
            )
        }
    }
}