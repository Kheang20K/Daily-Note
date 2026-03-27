package com.myapp.dailynote.presentation.screen.home


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@Composable
fun DraggableCardComplex(
    card: String,
    isRevealed: Boolean,
    cardOffset : Float,
    onExpand: () -> Unit,
    onCollapse: () -> Unit,
    content: @Composable (progress: Float) -> Unit
){

    val scope = rememberCoroutineScope()
    val offsetX = remember { Animatable(0f) }

    val progress = (-offsetX.value / cardOffset)
        .coerceIn(0f, 1f)

    // Sync UI with state
    LaunchedEffect(isRevealed) {
        if (isRevealed) {
            offsetX.animateTo(-cardOffset)
        } else {
            offsetX.animateTo(0f)
        }
    }

    Box {
        content(progress)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .offset { IntOffset(offsetX.value.toInt(), 0) }
                .pointerInput(Unit) {

                    detectHorizontalDragGestures(

                        onDragEnd = {
                            scope.launch {
                                if (offsetX.value <= -cardOffset / 2) {
                                    offsetX.animateTo(
                                        targetValue = -cardOffset,
                                        animationSpec = spring(
                                            dampingRatio = 0.7f,
                                            stiffness = Spring.StiffnessMedium
                                        )
                                    )
//                                offsetX.animateTo(-cardOffset)
                                    onExpand()
                                } else {
                                    offsetX.animateTo(0f)
                                    onCollapse()
                                }
                            }
                        }

                    ) { change, dragAmount ->

                        change.consume()

                        scope.launch {
                            val newOffset = (offsetX.value + dragAmount)
                                .coerceIn(-cardOffset, 0f)

                            offsetX.snapTo(newOffset)
                        }
                    }
                }
        ) {
            Text(
                text = card,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}


data class CardModel(
    val id : Int,
    val title : String
)