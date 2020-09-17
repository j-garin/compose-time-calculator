package com.jgarin.composecalculator.ui.addedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.jgarin.composecalculator.repository.repository
import com.jgarin.composecalculator.ui.addedit.components.CreateEditContent
import com.jgarin.composecalculator.usecase.CreateItemUseCase
import com.jgarin.composecalculator.usecase.UpdateItemUseCase

class CreateItemFragment : Fragment() {

    private val args: CreateItemFragmentArgs by navArgs()
    private val viewModel: CreateEditViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return CreateEditViewModel(
                    args.wokrListItem,
                    CreateItemUseCase(repository),
                    UpdateItemUseCase(repository)
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
                CreateEditContent(viewModel) { activity?.onBackPressed() }
            }
        }
    }
}