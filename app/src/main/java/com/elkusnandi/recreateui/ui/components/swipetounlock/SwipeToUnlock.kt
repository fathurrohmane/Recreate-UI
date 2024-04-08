package com.elkusnandi.recreateui.ui.components.swipetounlock

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwipeToUnlockButton(
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    onSwiped: () -> Unit
) {
    Surface(
        modifier = modifier
            .heightIn(min = 56.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(100f),
    ) {
        val state = remember {
            AnchoredDraggableState(
                initialValue = DragAnchors.Start,
                positionalThreshold = { distance: Float -> distance * 0.95f },
                velocityThreshold = { 100.dp.value },
                animationSpec = tween(),
            )
        }

        val contentSize = 56.dp
        val contentSizePx = with(LocalDensity.current) { contentSize.toPx() }

        SideEffect {
            if (!state.offset.isNaN() && state.requireOffset() >= state.anchors.maxAnchor()) {
                onSwiped()
            }
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .onSizeChanged { layoutSize ->
                    val dragEndPoint = layoutSize.width - contentSizePx
                    state.updateAnchors(
                        DraggableAnchors {
                            DragAnchors.entries
                                .forEach { anchor ->
                                    anchor at dragEndPoint * anchor.fraction
                                }
                        }
                    )
                }
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(horizontal = 4.dp)
        ) {
            content()
            Surface(
                modifier = Modifier
                    .offset {
                        IntOffset(
                            x = state
                                .requireOffset()
                                .roundToInt(),
                            y = 0,
                        )
                    }
                    .anchoredDraggable(state, Orientation.Horizontal)
                    .align(Alignment.CenterStart)
                    .size(48.dp),
                shape = RoundedCornerShape(100f)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

enum class DragAnchors(val fraction: Float) {
    Start(0f),
    End(1f),
}

@Preview
@Composable
private fun SwipeToUnlockButtonPreview() {
    val text = remember {
        mutableStateOf("Swipe to confirm")
    }
    MaterialTheme {
        SwipeToUnlockButton({
            Text(text = text.value)
        }) {
            text.value = "Swipped"
        }
    }
}