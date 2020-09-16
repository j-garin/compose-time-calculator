package com.jgarin.composecalculator.ui.main

import androidx.lifecycle.MutableLiveData
import com.jgarin.composecalculator.base.BaseViewModel
import com.jgarin.composecalculator.base.flatMap
import com.jgarin.composecalculator.base.map
import com.jgarin.composecalculator.ui.data.MainScreenListItem
import com.jgarin.composecalculator.usecase.AddEmptyItemUseCase
import com.jgarin.composecalculator.usecase.CalculateTotalUseCase
import com.jgarin.composecalculator.usecase.RemoveItemUseCase
import com.jgarin.composecalculator.usecase.UpdateItemUseCase

class MainViewModel(
    private val addItemUseCase: AddEmptyItemUseCase,
    private val removeItemUseCase: RemoveItemUseCase,
    private val calculateTotalUseCase: CalculateTotalUseCase,
    private val updateItemUseCase: UpdateItemUseCase,
) : BaseViewModel() {

    val stateStream = MutableLiveData<MainScreenState>().apply { value = MainScreenState.default }

    fun addItem() = launch {
        val list = stateStream.value!!.items
        addItemUseCase(AddEmptyItemUseCase.Params(list))
            .doOnSuccess { result -> stateStream.value = stateStream.value?.copy(items = result) }
            .doOnError(::handleError)
    }.addToLoadingState()

    fun removeItem(item: MainScreenListItem) = launch {
        removeItemUseCase(RemoveItemUseCase.Params(stateStream.value!!.items, item))
            .flatMap { result -> calculateTotalUseCase(result).map { it to result } }
            .doOnSuccess { pair ->
                stateStream.value =
                    stateStream.value?.copy(items = pair.second, total = pair.first)
            }
            .doOnError(::handleError)
    }.addToLoadingState()

    fun updateItem(id: Long, value: String) = launch {
        updateItemUseCase(UpdateItemUseCase.Params(stateStream.value!!.items, id, value))
            .flatMap { result -> calculateTotalUseCase(result).map { it to result } }
            .doOnSuccess { result ->
                stateStream.value =
                    stateStream.value?.copy(items = result.second, total = result.first)
            }
            .doOnError(::handleError)
    }
}