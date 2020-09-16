package com.jgarin.composecalculator.usecase

import com.jgarin.composecalculator.base.BaseUseCase
import com.jgarin.composecalculator.base.Try
import com.jgarin.composecalculator.data.WorkDuration
import com.jgarin.composecalculator.ui.data.MainScreenListItem
import com.jgarin.composecalculator.ui.data.WorkListItem

class UpdateItemUseCase : BaseUseCase<UpdateItemUseCase.Params, List<MainScreenListItem>>() {

    private val numRegex = "[0-9]".toRegex()

    data class Params(val list: List<MainScreenListItem>, val id: Long, val value: String)

    override suspend fun run(params: Params): Try<List<MainScreenListItem>> {
        val split = params.value.filter { it.toString().matches(numRegex) }.toInt()
        val newHours = split / 100
        val newMinutes = split % 100
        if (newMinutes >= 60) return Try.Error(Error("There cannot be more than 60 minutes in an hour"))

        val index = params.list.indexOfFirst { (it as? WorkListItem)?.id == params.id }
        if (index < 0) return Try.Error(Error("Item with id ${params.id} not found"))

        val newItem =
            (params.list[index] as WorkListItem).copy(duration = WorkDuration(newHours, newMinutes))
        val newList = ArrayList(params.list).apply { set(index, newItem) }

        return Try.Success(newList)
    }
}