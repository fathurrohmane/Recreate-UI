package com.elkusnandi.recreateui.ui.components.swipetounlock

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun SwipeToUnlockExample() {
    MaterialTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            val context = LocalContext.current
            SwipeToUnlockButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp, 32.dp),
                content = {
                    Text(text = "Swipe to unlock")
                }
            ) {
                Toast.makeText(context, "Swiped", Toast.LENGTH_SHORT).show()
            }
        }
    }
}