package com.jgarin.composecalculator.ui.addedit.di

import com.jgarin.composecalculator.data.DurationUi
import com.jgarin.composecalculator.ui.addedit.CreateEditViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val createEditModule = module {

    viewModel { (duration: DurationUi?) -> CreateEditViewModel(duration, get(), get()) }
}