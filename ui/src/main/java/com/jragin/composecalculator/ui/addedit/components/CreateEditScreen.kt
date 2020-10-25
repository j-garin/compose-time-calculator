package com.jragin.composecalculator.ui.addedit.components

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.gesture.ScrollCallback
import androidx.compose.ui.gesture.scrollGestureFilter
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.ui.tooling.preview.Preview
import com.jragin.composecalculator.ui.addedit.CreateEditViewModel

@Composable
fun CreateEditContent(viewModel: CreateEditViewModel, onCancel: () -> Unit) {
    MaterialTheme {
        Scaffold(topBar = { Toolbar(isEditMode = viewModel.isEditMode) }) {
            MainLayout(viewModel = viewModel, onCancel = onCancel)
        }
    }
}

@Composable
private fun Toolbar(isEditMode: Boolean) {
    TopAppBar {
        Text(
            text = if (isEditMode) "Edit Record" else "Create Record",
            modifier = Modifier.gravity(Alignment.CenterVertically)
                .padding(start = 16.dp)
        )
    }
}

@Composable
private fun MainLayout(viewModel: CreateEditViewModel, onCancel: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Content(viewModel, Modifier.weight(1f, true))
        Buttons(viewModel, onCancel)
    }
}

@Composable
private fun Content(viewModel: CreateEditViewModel, modifier: Modifier) {
    Column(modifier = modifier.fillMaxWidth().padding(16.dp)) {
        val typography = MaterialTheme.typography
        Text(
            text = "Swipe up and down\nfrom the view\nto modify the value",
            textAlign = TextAlign.Center,
            modifier = Modifier.gravity(Alignment.CenterHorizontally),
            style = typography.h5
        )

        Spacer(modifier = Modifier.height(32.dp))

        val textStyle = typography.h2
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.weight(1f, true))
            TimeText(
                liveData = viewModel.hours,
                textStyle = textStyle,
                onSwipe = viewModel::adjustHours
            )
            Text(text = ":", style = textStyle)
            TimeText(
                liveData = viewModel.minutes,
                textStyle = textStyle,
                onSwipe = viewModel::adjustMinutes
            )
            Spacer(modifier = Modifier.weight(1f, true))
        }
    }
}

@Composable
private fun TimeText(
    liveData: LiveData<Int>,
    textStyle: TextStyle,
    onSwipe: (Float) -> Unit,
) {
    val text = liveData.observeAsState(initial = 0).value
    Text(
        text = text.toString().padStart(2, '0'),
        modifier = Modifier.scrollGestureFilter(
            scrollCallback = AdjustScrollCallback(onSwipe),
            orientation = Orientation.Vertical
        ),
        style = textStyle,
    )
}

private class AdjustScrollCallback(private val adjust: (Float) -> Unit) : ScrollCallback {

    override fun onScroll(scrollDistance: Float): Float {
        adjust(-scrollDistance / SENSITIVITY)
        return scrollDistance
    }
}

private const val SENSITIVITY = 3f

@Composable
private fun Buttons(viewModel: CreateEditViewModel, onCancel: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)) {
        Spacer(modifier = Modifier.width(16.dp))
        Button("Cancel", onCancel)
        Spacer(modifier = Modifier.weight(1f, fill = true))
        Button("Save", viewModel::save)
        Spacer(modifier = Modifier.width(16.dp))
    }
}

@Composable
private fun Button(text: String, onClick: () -> Unit) {
    val cancelColor = MaterialTheme.colors.secondary
    Stack(
        modifier = Modifier.preferredHeight(48.dp)
            .preferredWidth(96.dp)
            .background(color = cancelColor, shape = RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
    ) {
        Text(text = text, modifier = Modifier.gravity(Alignment.Center))
    }
}

@Preview(name = "Edit mode toolbar")
@Composable
private fun editModeToolbar() {
    Toolbar(isEditMode = true)
}

@Preview(name = "Create mode toolbar")
@Composable
private fun createModeToolbar() {
    Toolbar(isEditMode = false)
}

@Preview(name = "button")
@Composable
private fun ButtonsPreview() {
    MaterialTheme {
        Button("Hello") {}
    }
}