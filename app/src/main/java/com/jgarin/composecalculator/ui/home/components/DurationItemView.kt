package com.jgarin.composecalculator.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.RowScope.gravity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.jgarin.composecalculator.data.DurationUi

@Composable
fun DurationItemView(
    item: DurationUi,
    onDeleteClicked: (DurationUi) -> Unit,
    onClicked: (DurationUi) -> Unit,
) {

    val text = when (item.minutes.toString().length) {
        1 -> "${item.hours}:0${item.minutes}"
        else -> "${item.hours}:${item.minutes}"
    }

    Row(
        modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(8.dp)
            .gravity(Alignment.CenterVertically)
            .clickable { onClicked(item) }
    ) {
        Text(modifier = Modifier.weight(1f, fill = true), text = text)
        Spacer(modifier = Modifier.width(8.dp))
        Image(
            Icons.Outlined.Delete,
            modifier = Modifier.preferredSize(24.dp).clickable { onDeleteClicked(item) }
        )
    }
}


@Preview
@Composable
private fun preview() {
    MaterialTheme {
        DurationItemView(
            item = DurationUi(0, 3, 0),
            onDeleteClicked = {},
            onClicked = {},
        )
    }
}