package com.elkusnandi.recreateui.ui.components.draggablefloatingbutton

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import kotlinx.coroutines.launch

/**
 * Floating button container that can be dragged through the screen and snapped into nearest side screen.
 *
 * @param modifier container modifier
 * @param floatingButtonAlignment the start position of the floating button
 * @param floatingButton slot for adding the floating button. Button need to use provided modifier.
 */
@Composable
fun DraggableFloatingButton(
    modifier: Modifier = Modifier,
    floatingButtonAlignment: Alignment = Alignment.BottomEnd,
    floatingButton: @Composable (modifier: Modifier) -> Unit,
) {
    var boxSize by remember {
        mutableStateOf(IntSize(0, 0))
    }
    var contentSize by remember {
        mutableStateOf(IntSize(0, 0))
    }
    var endXPosition by remember {
        mutableIntStateOf(0)
    }
    var endYPosition by remember {
        mutableIntStateOf(0)
    }

    val translationX = remember { Animatable(0f) }
    val translationY = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        endXPosition = boxSize.width - contentSize.width
        endYPosition = boxSize.height - contentSize.height

        when (floatingButtonAlignment) {
            Alignment.BottomEnd -> {
                translationX.snapTo(endXPosition.toFloat())
                translationY.snapTo(endYPosition.toFloat())
            }

            Alignment.BottomStart -> {
                translationX.snapTo(0f)
                translationY.snapTo(endYPosition.toFloat())
            }

            Alignment.BottomCenter -> {
                translationX.snapTo(endXPosition.toFloat() / 2)
                translationY.snapTo(endYPosition.toFloat())
            }

            Alignment.CenterStart -> {
                translationX.snapTo(0f)
                translationY.snapTo(endYPosition.toFloat() / 2)
            }

            Alignment.Center -> {
                translationX.snapTo(endXPosition.toFloat() / 2)
                translationY.snapTo(endYPosition.toFloat() / 2)
            }

            Alignment.CenterEnd -> {
                translationX.snapTo(endXPosition.toFloat())
                translationY.snapTo(endYPosition.toFloat() / 2)
            }

            Alignment.TopStart -> {
                translationX.snapTo(0f)
                translationY.snapTo(0f)
            }

            Alignment.TopCenter -> {
                translationX.snapTo(endXPosition.toFloat() / 2)
                translationY.snapTo(0f)
            }

            Alignment.TopEnd -> {
                translationX.snapTo(endXPosition.toFloat())
                translationY.snapTo(0f)
            }
        }
    }

    val coroutineScope = rememberCoroutineScope()

    Box(modifier = modifier
        .onSizeChanged {
            boxSize = it
        }) {
        floatingButton(
            Modifier
                .onSizeChanged {
                    contentSize = it
                }
                .graphicsLayer {
                    this.translationX = translationX.value
                    this.translationY = translationY.value
                }
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragEnd = {
                            if (translationX.value < 0f) {
                                coroutineScope.launch {
                                    translationX.animateTo(0f)
                                }
                            } else if (translationX.value > endXPosition) {
                                coroutineScope.launch {
                                    translationX.animateTo(endXPosition.toFloat())
                                }
                            }

                            if (translationY.value < 0f) {
                                coroutineScope.launch {
                                    translationY.animateTo(0f)
                                }
                            } else if (translationY.value > endYPosition) {
                                coroutineScope.launch {
                                    translationY.animateTo(endYPosition.toFloat())
                                }
                            }

                            if (translationX.value > 0 || translationX.value < endXPosition) {
                                val newOffset =
                                    if (translationX.value + contentSize.width / 2 > boxSize.width / 2) {
                                        endXPosition.toFloat()
                                    } else {
                                        0f
                                    }
                                coroutineScope.launch {
                                    translationX.animateTo(newOffset)
                                }
                            }

                        }
                    ) { change, dragAmount ->
                        change.consume()
                        coroutineScope.launch {
                            translationX.snapTo(translationX.value + dragAmount.x)
                            translationY.snapTo(translationY.value + dragAmount.y)
                        }
                    }
                })
    }
}