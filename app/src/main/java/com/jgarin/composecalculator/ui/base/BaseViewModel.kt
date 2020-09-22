package com.jgarin.composecalculator.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlin.properties.Delegates

abstract class BaseViewModel : ViewModel() {

    val error: MutableLiveData<Throwable> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    var isAutoRefreshing = false

    protected var loadingCounter by Delegates.observable(0) { _, _, newValue ->
        if (!isAutoRefreshing) {
            isLoading.value = (newValue > 0)
        }
    }

    protected fun launch(block: suspend CoroutineScope.() -> Unit): Job =
        viewModelScope.launch(Dispatchers.Main, block = block)

    protected open fun handleError(error: Throwable) {
        this.error.value = error
    }

    protected fun Job.addToLoadingState() {
        loadingCounter++

        invokeOnCompletion {
            viewModelScope.launch(Dispatchers.Main) { loadingCounter-- }
        }
    }
}