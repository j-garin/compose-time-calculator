package com.jgarin.composecalculator.usecase

import com.jgarin.composecalculator.usecase.base.BaseUseCase
import com.jgarin.composecalculator.usecase.base.Try
import com.jgarin.composecalculator.uimodels.DurationUi

class CalculateTotalUseCase : BaseUseCase<List<DurationUi>, DurationUi>() {

    override suspend fun run(params: List<DurationUi>): Try<DurationUi> {
        if (params.isEmpty()) return Try.Success(DurationUi(0, 0, 0))
        return Try.Success(
            params.reduce { acc, item ->
                val minutesSum = acc.minutes + item.minutes
                DurationUi(
                    id = 0,
                    hours = acc.hours + item.hours + minutesSum / 60,
                    minutes = minutesSum % 60
                )
            }
        )
    }
}