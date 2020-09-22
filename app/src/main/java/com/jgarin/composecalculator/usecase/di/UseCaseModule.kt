package com.jgarin.composecalculator.usecase.di

import com.jgarin.composecalculator.usecase.*
import org.koin.dsl.module

val useCaseModule = module {

    factory { CalculateTotalUseCase() }
    factory { CreateItemUseCase(get()) }
    factory { ReadItemsUseCase(get()) }
    factory { RemoveItemUseCase(get()) }
    factory { UpdateItemUseCase(get()) }
}