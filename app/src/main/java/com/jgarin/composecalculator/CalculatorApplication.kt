package com.jgarin.composecalculator

import android.app.Application
import com.jgarin.composecalculator.repository.di.repositoryModule
import com.jgarin.composecalculator.ui.addedit.di.createEditModule
import com.jgarin.composecalculator.ui.home.di.homeModule
import com.jgarin.composecalculator.usecase.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CalculatorApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CalculatorApplication)
            modules(repositoryModule, useCaseModule, homeModule, createEditModule)
        }
    }
}