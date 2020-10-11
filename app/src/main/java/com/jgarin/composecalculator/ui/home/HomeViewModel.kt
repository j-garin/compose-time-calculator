package com.jgarin.composecalculator.ui.home

import androidx.lifecycle.MutableLiveData
import com.jgarin.composecalculator.mappers.DurationDomainToUiMapper
import com.jgarin.composecalculator.models.DurationDomain
import com.jgarin.composecalculator.ui.base.BaseViewModel
import com.jgarin.composecalculator.uimodels.AddMoreListItem
import com.jgarin.composecalculator.uimodels.DurationUi
import com.jgarin.composecalculator.uimodels.MainScreenListItem
import com.jgarin.composecalculator.usecase.CalculateTotalUseCase
import com.jgarin.composecalculator.usecase.ReadItemsUseCase
import com.jgarin.composecalculator.usecase.RemoveItemUseCase
import com.jgarin.composecalculator.usecase.base.map

class HomeViewModel(
    readItemsUseCase: ReadItemsUseCase,
    private val removeItemUseCase: RemoveItemUseCase,
    private val calculateTotalUseCase: CalculateTotalUseCase,
    private val domainToUiMapper: DurationDomainToUiMapper,
) : BaseViewModel() {

    val items = MutableLiveData<List<MainScreenListItem>>()
        .apply { value = listOf(AddMoreListItem) }
    val total = MutableLiveData<DurationUi>()
        .apply { value = DurationUi(0, 0, 0) }

    init {
        launch {
            for (list in readItemsUseCase()) {
                items.value = list.map { item -> domainToUiMapper(item) }
                    .toMutableList<MainScreenListItem>()
                    .apply { add(AddMoreListItem) }
                calculateTotal(list)
            }
        }
    }

    fun removeItem(item: DurationUi) = launch {
        removeItemUseCase(RemoveItemUseCase.Params(item.id))
            .doOnError(::handleError)
    }.addToLoadingState()

    private fun calculateTotal(list: List<DurationDomain>) = launch {
        calculateTotalUseCase(list)
            .map { item -> domainToUiMapper(item) }
            .doOnError(::handleError)
            .doOnSuccess(total::setValue)
    }
}