package com.elkusnandi.recreateui.ui.components.draggablefloatingbutton

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun DraggableFloatingButtonExample() {
    MaterialTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            Button(onClick = {}) {
                Text(text = "Back button")
            }
            DraggableFloatingButton(
                modifier = Modifier.fillMaxSize(),
            ) {
                FloatingActionButton(
                    onClick = { },
                    modifier = it.padding(16.dp)
                ) {
                    Image(imageVector = Icons.Default.Add, contentDescription = "")
                }
            }
        }
    }
}