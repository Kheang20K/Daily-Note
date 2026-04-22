package com.myapp.dailynote.presentation.screen.home


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.myapp.dailynote.R
import com.myapp.dailynote.presentation.component.CardTodoList
import kotlinx.coroutines.launch


@Composable
fun DraggableCardComplex(
    title: String,
    date:Long?,
    onChecked: Boolean,
    onClick: () -> Unit,
    onClickFlag: () -> Unit,
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
//                .offset { IntOffset(offsetX.value.toInt(), 0) }
                .graphicsLayer {
                    translationX = offsetX.value
//                    shadowElevation = 1.dp.toPx()
                    shape = RoundedCornerShape(12.dp)
                    clip = false
                }
                .shadow(1.dp, RoundedCornerShape(12.dp))
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
                },
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFAFAFA)
            )
        ) {

            Row (
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Row(
                    modifier = Modifier
                        .weight(0.9f)
                        .padding(start = 12.dp)
                        .clickable{
                            onClick()
                        },
                ){
                    CardTodoList(
                        title = title,
                        dueDate = date,
                        onChecked = onChecked,
                    )

                }
                Row (
                    modifier = Modifier
                        .weight(0.1f)

                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_flage),
                        contentDescription = null,
                        modifier = Modifier
                            .size(28.dp)
                            .clickable {
                                onClickFlag()
                            }
                    )
                }
            }
        }
    }
}
