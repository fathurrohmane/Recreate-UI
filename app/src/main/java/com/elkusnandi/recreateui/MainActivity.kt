package com.elkusnandi.recreateui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.elkusnandi.recreateui.ui.components.draggablefloatingbutton.DraggableFloatingButton
import com.elkusnandi.recreateui.ui.theme.RecreateUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecreateUITheme {
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
        }
    }
}