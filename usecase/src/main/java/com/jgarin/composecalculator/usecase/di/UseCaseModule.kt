package com.jgarin.composecalculator.usecase.di

import com.jgarin.composecalculator.usecase.*
import org.koin.dsl.module

val useCaseModule = module {

    factory { CalculateTotalUseCase(get(), get()) }
    factory { CreateItemUseCase(get()) }
    factory { ReadItemsUseCase(get(), get()) }
    factory { RemoveItemUseCase(get()) }
    factory { UpdateItemUseCase(get()) }
}