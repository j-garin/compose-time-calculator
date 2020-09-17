package com.jgarin.composecalculator.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview

@Composable
fun AddNewItemView(onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Text(
            text = "Add Item",
            Modifier.weight(1f, fill = true).gravity(Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Image(Icons.Outlined.Add, modifier = Modifier.preferredSize(24.dp))
    }
}

@Preview
@Composable
private fun preview() {
    MaterialTheme {
        AddNewItemView {}
    }
}