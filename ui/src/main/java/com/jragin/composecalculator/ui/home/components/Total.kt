package com.jragin.composecalculator.ui.home.components

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.jgarin.composecalculator.uimodels.DurationUi

@Composable
fun Total(total: DurationUi) {
    val text = "Total: " + when (total.minutes.toString().length) {
        1 -> "${total.hours}:0${total.minutes}"
        else -> "${total.hours}:${total.minutes}"
    }
    Text(text, modifier = Modifier.padding(start = 16.dp), textAlign = TextAlign.Center)
}

@Preview
@Composable
private fun preview() {
    MaterialTheme {
        Total(total = DurationUi(0, 1, 23))
    }
}