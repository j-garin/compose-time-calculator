package com.jragin.composecalculator.ui.home

import androidx.lifecycle.MutableLiveData
import com.jragin.composecalculator.ui.base.BaseViewModel
import com.jgarin.composecalculator.uimodels.AddMoreListItem
import com.jgarin.composecalculator.uimodels.DurationUi
import com.jgarin.composecalculator.uimodels.MainScreenListItem
import com.jgarin.composecalculator.usecase.CalculateTotalUseCase
import com.jgarin.composecalculator.usecase.ReadItemsUseCase
import com.jgarin.composecalculator.usecase.RemoveItemUseCase
import kotlinx.coroutines.flow.collect

class HomeViewModel(
    private val readItemsUseCase: ReadItemsUseCase,
    private val removeItemUseCase: RemoveItemUseCase,
    private val calculateTotalUseCase: CalculateTotalUseCase,
) : BaseViewModel() {

    val items = MutableLiveData<List<MainScreenListItem>>()
        .apply { value = listOf(AddMoreListItem) }
    val total = MutableLiveData<DurationUi>()
        .apply { value = DurationUi(0, 0, 0) }

    init {
        loadItems()
    }

    private fun loadItems() {
        launch {
            readItemsUseCase(Unit)
                .doOnError(::handleError)
                .doOnSuccess { flow ->
                    flow.collect { list ->
                        items.value = list.toMutableList<MainScreenListItem>()
                            .apply { add(AddMoreListItem) }
                        calculateTotal(list)
                    }
                }
        }
    }

    fun removeItem(item: DurationUi) = launch {
        removeItemUseCase(RemoveItemUseCase.Params(item.id))
            .doOnError(::handleError)
    }.addToLoadingState()

    private fun calculateTotal(list: List<DurationUi>) = launch {
        calculateTotalUseCase(list)
            .doOnError(::handleError)
            .doOnSuccess(total::setValue)
    }
}