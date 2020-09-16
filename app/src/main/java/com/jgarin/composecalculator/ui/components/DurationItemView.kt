package com.jgarin.composecalculator.ui.components

import androidx.compose.foundation.BaseTextField
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.RowScope.gravity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.jgarin.composecalculator.data.WorkDuration
import com.jgarin.composecalculator.ui.data.WorkListItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DurationItemView(
    item: WorkListItem,
    onDeleteClicked: (WorkListItem) -> Unit,
    onTextChanged: (String) -> Unit
) {

    val text = when (item.duration.minutes.toString().length) {
        1 -> "${item.duration.hours}:0${item.duration.minutes}"
        else -> "${item.duration.hours}:${item.duration.minutes}"
    }

    Row(
        modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(8.dp)
            .gravity(Alignment.CenterVertically)
    ) {
        BaseTextField(
            modifier = Modifier.weight(1f, fill = true),
            value = TextFieldValue(text = text, selection = TextRange(text.length, text.length)),
            onValueChange = { onTextChanged(it.text) },
            keyboardType = KeyboardType.Number,
        )

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
        DurationItemView(item = WorkListItem(0, WorkDuration(3, 0)), onDeleteClicked = {}, onTextChanged = {})
    }
}