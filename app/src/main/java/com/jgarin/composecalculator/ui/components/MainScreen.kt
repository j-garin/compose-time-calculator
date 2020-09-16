package com.jgarin.composecalculator.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import com.jgarin.composecalculator.ui.data.AddMoreListItem
import com.jgarin.composecalculator.ui.data.WorkListItem
import com.jgarin.composecalculator.ui.main.MainScreenState
import com.jgarin.composecalculator.ui.main.MainViewModel

@Composable
fun MainScreenContent(viewModel: MainViewModel) {

    val stateStream: LiveData<MainScreenState> = viewModel.stateStream
    val state = stateStream.observeAsState(initial = MainScreenState.default).value

    MaterialTheme {
        Scaffold(
            topBar = { TopAppBar { Total(total = state.total) } },
        ) {
            Column(
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                horizontalGravity = Alignment.CenterHorizontally,
            ) {
                state.items.forEach { item ->
                    when (item) {
                        is AddMoreListItem -> AddNewItemView(onClick = { viewModel.addItem() })
                        is WorkListItem -> DurationItemView(
                            item = item,
                            onDeleteClicked = { viewModel.removeItem(item) },
                            onTextChanged = { viewModel.updateItem(item.id, it) })
                    }
                }
            }
        }
    }
}