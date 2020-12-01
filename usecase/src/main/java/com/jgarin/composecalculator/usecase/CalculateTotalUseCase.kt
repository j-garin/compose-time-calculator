package com.jgarin.composecalculator.usecase

import com.jgarin.composecalculator.mappers.DurationDomainToUiMapper
import com.jgarin.composecalculator.models.DurationDomain
import com.jgarin.composecalculator.repository.DataRepository
import com.jgarin.composecalculator.uimodels.DurationUi
import com.jgarin.composecalculator.usecase.base.Try
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CalculateTotalUseCase(
    private val repo: DataRepository,
    private val mapper: DurationDomainToUiMapper
) {

    suspend operator fun invoke(): Flow<Try<DurationUi>> = repo.readItems().map {
        Try {
            if (it.isEmpty()) {
                DurationUi(0, 0, 0)
            } else {
                val durationDomain = it.reduce { acc, item ->
                    val minutesSum = acc.minutes + item.minutes
                    DurationDomain(
                        id = 0,
                        hours = acc.hours + item.hours + minutesSum / 60,
                        minutes = minutesSum % 60
                    )
                }
                mapper(durationDomain)
            }
        }
    }
}