package com.jgarin.composecalculator.usecase

import com.jgarin.composecalculator.base.BaseUseCase
import com.jgarin.composecalculator.base.Try
import com.jgarin.composecalculator.data.WorkDuration
import com.jgarin.composecalculator.ui.data.MainScreenListItem
import com.jgarin.composecalculator.ui.data.WorkListItem

class CalculateTotalUseCase : BaseUseCase<List<MainScreenListItem>, WorkDuration>() {

    override suspend fun run(params: List<MainScreenListItem>): Try<WorkDuration> {
        val workDurations = params.filterIsInstance<WorkListItem>()
        if (workDurations.isEmpty()) return Try.Success(WorkDuration(0, 0))
        return Try.Success(
            workDurations.map { it.duration }
                .reduce { acc, item ->
                    val minutesSum = acc.minutes + item.minutes
                    WorkDuration(
                        hours = acc.hours + item.hours + minutesSum / 60,
                        minutes = minutesSum % 60
                    )
                }
        )
    }
}