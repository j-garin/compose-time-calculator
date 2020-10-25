package com.jgarrin.composecalculator.di

import android.app.Application
import com.jgarin.composecalculator.mappers.di.mappersModule
import com.jgarin.composecalculator.repository.di.repositoryModule
import com.jgarin.composecalculator.usecase.di.useCaseModule
import com.jragin.composecalculator.ui.addedit.di.createEditModule
import com.jragin.composecalculator.ui.home.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

private val modules = listOf(
    mappersModule,
    repositoryModule,
    useCaseModule,
    // UI
    homeModule,
    createEditModule
)

fun Application.initializeKoin() = startKoin {
    androidContext(this@initializeKoin)
    modules(modules)
}
