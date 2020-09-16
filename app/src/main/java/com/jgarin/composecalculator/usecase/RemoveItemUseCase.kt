package com.jgarin.composecalculator.usecase

import com.jgarin.composecalculator.base.BaseUseCase
import com.jgarin.composecalculator.base.Try
import com.jgarin.composecalculator.extensions.toArrayList
import com.jgarin.composecalculator.ui.data.MainScreenListItem

class RemoveItemUseCase : BaseUseCase<RemoveItemUseCase.Params, List<MainScreenListItem>>() {

    data class Params(val items: List<MainScreenListItem>, val itemToRemove: MainScreenListItem)

    override suspend fun run(params: Params): Try<List<MainScreenListItem>> {
        return Try.Success(params.items.toArrayList().apply { remove(params.itemToRemove) })
    }
}