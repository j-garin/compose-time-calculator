package com.jgarin.composecalculator.ui.addedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jgarin.composecalculator.ui.addedit.components.CreateEditContent
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CreateItemFragment : Fragment() {

    private val args: CreateItemFragmentArgs by navArgs()
    private val viewModel: CreateEditViewModel by viewModel { parametersOf(args.wokrListItem) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.saveCompleteEvent.observe(viewLifecycleOwner) { singleEvent ->
            singleEvent?.event?.let { findNavController().popBackStack() }
        }

        return ComposeView(requireContext()).apply {
            setContent {
                CreateEditContent(viewModel) { activity?.onBackPressed() }
            }
        }
    }
}