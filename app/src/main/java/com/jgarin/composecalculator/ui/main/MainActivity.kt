package com.jgarin.composecalculator.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jgarin.composecalculator.ui.components.MainScreenContent
import com.jgarin.composecalculator.usecase.AddEmptyItemUseCase
import com.jgarin.composecalculator.usecase.CalculateTotalUseCase
import com.jgarin.composecalculator.usecase.RemoveItemUseCase
import com.jgarin.composecalculator.usecase.UpdateItemUseCase

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(
                    AddEmptyItemUseCase(),
                    RemoveItemUseCase(),
                    CalculateTotalUseCase(),
                    UpdateItemUseCase()
                ) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreenContent(viewModel)
        }
    }
}

