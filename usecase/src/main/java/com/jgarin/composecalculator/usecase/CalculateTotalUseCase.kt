package com.jgarin.composecalculator.usecase

import com.jgarin.composecalculator.models.DurationDomain
import com.jgarin.composecalculator.usecase.base.BaseUseCase
import com.jgarin.composecalculator.usecase.base.Try

class CalculateTotalUseCase : BaseUseCase<List<DurationDomain>, DurationDomain>() {

    override suspend fun run(params: List<DurationDomain>): Try<DurationDomain> {
        if (params.isEmpty()) return Try.Success(DurationDomain(0, 0, 0))
        return Try.Success(
            params.reduce { acc, item ->
                val minutesSum = acc.minutes + item.minutes
                DurationDomain(
                    id = 0,
                    hours = acc.hours + item.hours + minutesSum / 60,
                    minutes = minutesSum % 60
                )
            }
        )
    }
}