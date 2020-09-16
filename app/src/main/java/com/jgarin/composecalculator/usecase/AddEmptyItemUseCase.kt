package com.jgarin.composecalculator.usecase

import com.jgarin.composecalculator.base.BaseUseCase
import com.jgarin.composecalculator.base.Try
import com.jgarin.composecalculator.data.WorkDuration
import com.jgarin.composecalculator.extensions.toArrayList
import com.jgarin.composecalculator.ui.data.MainScreenListItem
import com.jgarin.composecalculator.ui.data.WorkListItem

class AddEmptyItemUseCase : BaseUseCase<AddEmptyItemUseCase.Params, List<MainScreenListItem>>() {

    data class Params(val list: List<MainScreenListItem>)

    override suspend fun run(params: Params): Try<List<MainScreenListItem>> {
        return Try.Success(
            params.list.toArrayList().apply {
                add(params.list.lastIndex, WorkListItem(id = System.currentTimeMillis(), duration = WorkDuration(0, 0)))
            }
        )
    }
}