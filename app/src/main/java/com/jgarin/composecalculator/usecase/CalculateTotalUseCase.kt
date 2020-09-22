package com.jgarin.composecalculator.usecase

import com.jgarin.composecalculator.base.BaseUseCase
import com.jgarin.composecalculator.base.Try
import com.jgarin.composecalculator.data.DurationUi
import com.jgarin.composecalculator.data.MainScreenListItem

class CalculateTotalUseCase : BaseUseCase<List<MainScreenListItem>, DurationUi>() {

    override suspend fun run(params: List<MainScreenListItem>): Try<DurationUi> {
        val durations = params.filterIsInstance<DurationUi>()
        if (durations.isEmpty()) return Try.Success(DurationUi(0, 0, 0))
        return Try.Success(
            durations.reduce { acc, item ->
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