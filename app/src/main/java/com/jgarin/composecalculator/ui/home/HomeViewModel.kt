package com.jgarin.composecalculator.ui.home

import androidx.lifecycle.MutableLiveData
import com.jgarin.composecalculator.base.BaseViewModel
import com.jgarin.composecalculator.uimodels.AddMoreListItem
import com.jgarin.composecalculator.uimodels.DurationUi
import com.jgarin.composecalculator.uimodels.MainScreenListItem
import com.jgarin.composecalculator.mappers.DurationDomainToUiMapper
import com.jgarin.composecalculator.mappers.DurationUiToDomainMapper
import com.jgarin.composecalculator.usecase.base.map

class HomeViewModel(
    readItemsUseCase: com.jgarin.composecalculator.usecase.ReadItemsUseCase,
    private val removeItemUseCase: com.jgarin.composecalculator.usecase.RemoveItemUseCase,
    private val calculateTotalUseCase: com.jgarin.composecalculator.usecase.CalculateTotalUseCase,
    private val domainToUiMapper: DurationDomainToUiMapper,
    private val uiToDomainMapper: DurationUiToDomainMapper,
) : BaseViewModel() {

    val items =
        MutableLiveData<List<MainScreenListItem>>().apply { value = listOf(AddMoreListItem) }
    val total = MutableLiveData<DurationUi>().apply { value = DurationUi(0, 0, 0) }

    init {
        launch {
            for (list in readItemsUseCase()) {
                items.value =
                    ArrayList<MainScreenListItem>(list.map { item -> domainToUiMapper(item) })
                        .apply { add(AddMoreListItem) }
                calculateTotal()
            }
        }
    }

    fun removeItem(item: DurationUi) = launch {
        removeItemUseCase(com.jgarin.composecalculator.usecase.RemoveItemUseCase.Params(item.id)).doOnError(
            ::handleError
        )
    }.addToLoadingState()

    private fun calculateTotal() = launch {
        calculateTotalUseCase(
            items.value!!.filterIsInstance(DurationUi::class.java)
                .map { item -> uiToDomainMapper(item) })
            .map { item -> domainToUiMapper(item) }
            .doOnError(::handleError)
            .doOnSuccess(total::setValue)
    }
}