package com.jgarin.composecalculator.ui.addedit

import androidx.lifecycle.MutableLiveData
import com.jgarin.composecalculator.base.BaseViewModel
import com.jgarin.composecalculator.base.SingleEvent
import com.jgarin.composecalculator.uimodels.DurationUi

class CreateEditViewModel(
    durationItem: DurationUi?,
    private val createItemUseCase: com.jgarin.composecalculator.usecase.CreateItemUseCase,
    private val updateItemUseCase: com.jgarin.composecalculator.usecase.UpdateItemUseCase,
) : BaseViewModel() {

    private val id = durationItem?.id ?: -1L

    val isEditMode = durationItem != null

    val hours = MutableLiveData<Int>().apply { value = durationItem?.hours ?: 0 }
    val minutes = MutableLiveData<Int>().apply { value = durationItem?.minutes ?: 0 }
    val saveCompleteEvent = MutableLiveData<SingleEvent<Unit>?>()

    fun save() = launch {
        val hours = hours.value!!
        val minutes = minutes.value!!

        if (isEditMode) {
            updateItemUseCase(com.jgarin.composecalculator.usecase.UpdateItemUseCase.Params(id = id, hours = hours, minutes = minutes))
        } else {
            createItemUseCase(com.jgarin.composecalculator.usecase.CreateItemUseCase.Params(hours = hours, minutes = minutes))
        }
            .doOnError(::handleError)
            .doOnSuccess { saveCompleteEvent.value = SingleEvent(Unit) }
    }.addToLoadingState()

    fun adjustHours(offset: Float) {
        val newValue = hours.value!! + offset.toInt()
        hours.value = when {
            newValue < 0 -> 0
            newValue > 99 -> 99
            else -> newValue
        }
    }

    fun adjustMinutes(offset: Float) {
        val newValue = minutes.value!! + offset.toInt()
        minutes.value = when {
            newValue < 0 -> 0
            newValue > 59 -> 59
            else -> newValue
        }
    }
}