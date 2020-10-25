package com.jgarin.composecalculator

import android.app.Application
import com.jragin.composecalculator.ui.addedit.di.createEditModule
import com.jragin.composecalculator.ui.home.di.homeModule
import com.jgarrin.composecalculator.di.initializeKoin

class CalculatorApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeKoin()
    }
}