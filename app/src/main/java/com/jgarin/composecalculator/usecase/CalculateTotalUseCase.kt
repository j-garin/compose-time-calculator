package com.jgarin.composecalculator.usecase

import com.jgarin.composecalculator.base.BaseUseCase
import com.jgarin.composecalculator.base.Try
import com.jgarin.composecalculator.data.DurationItem
import com.jgarin.composecalculator.data.MainScreenListItem

class CalculateTotalUseCase : BaseUseCase<List<MainScreenListItem>, DurationItem>() {

    override suspend fun run(params: List<MainScreenListItem>): Try<DurationItem> {
        val durations = params.filterIsInstance<DurationItem>()
        if (durations.isEmpty()) return Try.Success(DurationItem(0, 0, 0))
        return Try.Success(
            durations
                .reduce { acc, item ->
                    val minutesSum = acc.minutes + item.minutes
                    DurationItem(
                        id = 0,
                        hours = acc.hours + item.hours + minutesSum / 60,
                        minutes = minutesSum % 60
                    )
                }
        )
    }
}