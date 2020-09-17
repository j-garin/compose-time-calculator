package com.jgarin.composecalculator.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.jgarin.composecalculator.repository.repository
import com.jgarin.composecalculator.data.DurationItem
import com.jgarin.composecalculator.ui.home.components.HomeScreenContent
import com.jgarin.composecalculator.usecase.CalculateTotalUseCase
import com.jgarin.composecalculator.usecase.ReadItemsUseCase
import com.jgarin.composecalculator.usecase.RemoveItemUseCase

class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return HomeViewModel(
                    ReadItemsUseCase(repository),
                    RemoveItemUseCase(repository),
                    CalculateTotalUseCase(),
                ) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                HomeScreenContent(viewModel, ::addItem, ::editItem)
            }
        }
    }

    private fun addItem() {
        val action = HomeFragmentDirections.actionScreenHomeToScreenCreate(null)
        findNavController().navigate(action)
    }

    private fun editItem(item: DurationItem) {
        val action = HomeFragmentDirections.actionScreenHomeToScreenCreate(item)
        findNavController().navigate(action)
    }
}