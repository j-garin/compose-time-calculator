package com.jgarin.composecalculator.usecase

import com.jgarin.composecalculator.mappers.DurationDomainToUiMapper
import com.jgarin.composecalculator.repository.DataRepository
import com.jgarin.composecalculator.uimodels.DurationUi
import com.jgarin.composecalculator.usecase.base.BaseUseCase
import com.jgarin.composecalculator.usecase.base.Try
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ReadItemsUseCase(
    private val repo: DataRepository,
    private val mapper: DurationDomainToUiMapper
) : BaseUseCase<Unit, Flow<List<DurationUi>>>() {

    override suspend fun run(params: Unit): Try<Flow<List<DurationUi>>> {
        return Try { repo.readItems().map { list -> list.map { mapper(it) } } }
    }
}