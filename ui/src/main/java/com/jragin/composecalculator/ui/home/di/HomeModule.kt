package com.jragin.composecalculator.ui.home.di

import com.jragin.composecalculator.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    viewModel { HomeViewModel(get(), get(), get()) }
}