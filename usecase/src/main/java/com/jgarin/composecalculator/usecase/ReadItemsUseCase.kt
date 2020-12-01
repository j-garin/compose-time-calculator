package com.jgarin.composecalculator.usecase

import com.jgarin.composecalculator.mappers.DurationDomainToUiMapper
import com.jgarin.composecalculator.repository.DataRepository
import com.jgarin.composecalculator.uimodels.DurationUi
import com.jgarin.composecalculator.usecase.base.Try
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ReadItemsUseCase(
    private val repo: DataRepository,
    private val mapper: DurationDomainToUiMapper
) {

    suspend operator fun invoke(): Flow<Try<List<DurationUi>>> {
        return repo.readItems().map { list -> Try { list.map { mapper(it) } } }
    }
}