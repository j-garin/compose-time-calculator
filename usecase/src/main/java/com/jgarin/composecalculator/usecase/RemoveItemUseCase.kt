package com.jgarin.composecalculator.usecase

import com.jgarin.composecalculator.repository.DataRepository
import com.jgarin.composecalculator.usecase.base.BaseUseCase
import com.jgarin.composecalculator.usecase.base.Try

class RemoveItemUseCase(private val repo: DataRepository) :
    BaseUseCase<RemoveItemUseCase.Params, Unit>() {

    data class Params(val itemId: Long)

    override suspend fun run(params: Params): Try<Unit> {
        return Try { repo.deleteItem(params.itemId) }
    }
}