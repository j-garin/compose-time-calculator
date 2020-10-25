package com.jragin.composecalculator.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jgarin.composecalculator.uimodels.DurationUi
import com.jragin.composecalculator.ui.home.components.HomeScreenContent
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()

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

    private fun editItem(item: DurationUi) {
        val action = HomeFragmentDirections.actionScreenHomeToScreenCreate(item)
        findNavController().navigate(action)
    }
}