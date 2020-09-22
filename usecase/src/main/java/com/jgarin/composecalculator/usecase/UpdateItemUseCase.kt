package com.jgarin.composecalculator.usecase

import com.jgarin.composecalculator.models.DurationDomain
import com.jgarin.composecalculator.repository.DataRepository
import com.jgarin.composecalculator.usecase.base.BaseUseCase
import com.jgarin.composecalculator.usecase.base.Try

class UpdateItemUseCase(private val repo: DataRepository) :
    BaseUseCase<UpdateItemUseCase.Params, Unit>() {

    data class Params(val id: Long, val hours: Int, val minutes: Int)

    override suspend fun run(params: Params): Try<Unit> {
        return Try {
            repo.updateItem(
                DurationDomain(
                    id = params.id,
                    hours = params.hours,
                    minutes = params.minutes,
                )
            )
        }
    }
}