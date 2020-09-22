package com.jgarin.composecalculator.ui.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jgarin.composecalculator.data.AddMoreListItem
import com.jgarin.composecalculator.data.DurationUi
import com.jgarin.composecalculator.ui.home.HomeViewModel

@Composable
fun HomeScreenContent(
    viewModel: HomeViewModel,
    addItem: () -> Unit,
    editItem: (DurationUi) -> Unit
) {
    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar {
                    val total = viewModel.total.observeAsState(DurationUi(0, 0, 0)).value
                    Total(total = total)
                }
            },
        ) {
            val items = viewModel.items.observeAsState(emptyList()).value
            LazyColumnFor(
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                horizontalGravity = Alignment.CenterHorizontally,
                items = items,
                itemContent = { item ->
                    when (item) {
                        is AddMoreListItem -> AddNewItemView(onClick = addItem)
                        is DurationUi -> DurationItemView(
                            item = item,
                            onDeleteClicked = { viewModel.removeItem(item) },
                            onClicked = editItem,
                        )
                    }
                },
            )
        }
    }
}