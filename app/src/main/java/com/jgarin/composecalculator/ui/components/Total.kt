package com.jgarin.composecalculator.ui.components

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.RowScope.gravity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.jgarin.composecalculator.data.WorkDuration

@Composable
fun Total(total: WorkDuration) {
    val text = "Total: " + when (total.minutes.toString().length) {
        1 -> "${total.hours}:0${total.minutes}"
        else -> "${total.hours}:${total.minutes}"
    }
    Text(text, modifier = Modifier.padding(start = 16.dp).gravity(Alignment.CenterVertically))
}

@Preview
@Composable
private fun preview() {
    MaterialTheme {
        Total(total = WorkDuration(1, 23))
    }
}