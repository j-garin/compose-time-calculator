package com.jgarin.composecalculator.ui.home

import androidx.lifecycle.MutableLiveData
import com.jgarin.composecalculator.base.BaseViewModel
import com.jgarin.composecalculator.data.AddMoreListItem
import com.jgarin.composecalculator.data.DurationItem
import com.jgarin.composecalculator.data.MainScreenListItem
import com.jgarin.composecalculator.usecase.CalculateTotalUseCase
import com.jgarin.composecalculator.usecase.ReadItemsUseCase
import com.jgarin.composecalculator.usecase.RemoveItemUseCase

class HomeViewModel(
    readItemsUseCase: ReadItemsUseCase,
    private val removeItemUseCase: RemoveItemUseCase,
    private val calculateTotalUseCase: CalculateTotalUseCase,
) : BaseViewModel() {

    val items =
        MutableLiveData<List<MainScreenListItem>>().apply { value = listOf(AddMoreListItem) }
    val total = MutableLiveData<DurationItem>().apply { value = DurationItem(0, 0, 0) }

    init {
        launch {
            for (list in readItemsUseCase()) {
                items.value = ArrayList<MainScreenListItem>(list).apply { add(AddMoreListItem) }
                calculateTotal()
            }
        }
    }

    fun removeItem(item: DurationItem) = launch {
        removeItemUseCase(RemoveItemUseCase.Params(item.id)).doOnError(::handleError)
    }.addToLoadingState()

    private fun calculateTotal() = launch {
        calculateTotalUseCase(items.value!!)
            .doOnError(::handleError)
            .doOnSuccess(total::setValue)
    }
}