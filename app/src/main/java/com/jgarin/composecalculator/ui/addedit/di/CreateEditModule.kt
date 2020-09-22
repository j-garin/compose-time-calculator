package com.jgarin.composecalculator.ui.addedit.di

import com.jgarin.composecalculator.data.DurationItem
import com.jgarin.composecalculator.ui.addedit.CreateEditViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val createEditModule = module {

    viewModel { (duration: DurationItem?) -> CreateEditViewModel(duration, get(), get()) }
}