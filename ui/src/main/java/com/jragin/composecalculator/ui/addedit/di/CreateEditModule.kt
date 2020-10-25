package com.jragin.composecalculator.ui.addedit.di

import com.jgarin.composecalculator.uimodels.DurationUi
import com.jragin.composecalculator.ui.addedit.CreateEditViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val createEditModule = module {

    viewModel { (duration: DurationUi?) -> CreateEditViewModel(duration, get(), get()) }
}