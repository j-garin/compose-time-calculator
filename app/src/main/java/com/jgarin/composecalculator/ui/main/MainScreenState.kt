package com.jgarin.composecalculator.ui.main

import com.jgarin.composecalculator.ui.data.AddMoreListItem
import com.jgarin.composecalculator.ui.data.MainScreenListItem
import com.jgarin.composecalculator.data.WorkDuration

data class MainScreenState(
    val total: WorkDuration,
    val items: List<MainScreenListItem>
) {
    companion object {
        val default = MainScreenState(WorkDuration(0, 0), listOf(AddMoreListItem))
    }
}