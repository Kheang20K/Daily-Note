package com.myapp.dailynote.presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.myapp.dailynote.data.TimerState
import com.myapp.dailynote.presentation.screen.detail.AllDetailViewModel
import com.myapp.dailynote.presentation.screen.detail.formatSeconds
import com.myapp.dailynote.presentation.screen.detail.formatTime
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun CounterTimer(
    remainingTime: Long,
    totalTime: Long,
    handleColor: Color,
    isRunning: Boolean,
    onToggle: () -> Unit,
    inactiveBarColor: Color,
    activeBarColor: Color,
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 5.dp,
) {
    var size by remember { mutableStateOf(IntSize.Zero) }

    // FORCE ZERO PROGRESS: This keeps the handle and foreground bar fixed at the start (Left)
//    val progressFraction = 0f

    val progressFraction =
        if (totalTime == 0L) 0f
        else 1f - (remainingTime.toFloat() / totalTime.toFloat())

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.onSizeChanged { size = it }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {

            if (size.width == 0 || size.height == 0) return@Canvas

            val strokePx = strokeWidth.toPx()

            val arcSize = Size(
                size.width.toFloat() - strokePx,
                size.height.toFloat() - strokePx
            )

            val arcOffset = Offset(strokePx / 2f, strokePx / 2f)

            // -215f pushes the starting point to the bottom-left quadrant
            val startAngle = -215f
            val totalSweep = 250f

            // BACKGROUND ARC (Full length)
            drawArc(
                color = inactiveBarColor,
                startAngle = startAngle,
                sweepAngle = totalSweep,
                useCenter = false,
                topLeft = arcOffset,
                size = arcSize,
                style = Stroke(strokePx, cap = StrokeCap.Round)
            )

            // PROGRESS ARC (Stays flat at the starting left boundary)
            drawArc(
                color = activeBarColor,
                startAngle = startAngle,
                sweepAngle = totalSweep * progressFraction, // Will be 0
                useCenter = false,
                topLeft = arcOffset,
                size = arcSize,
                style = Stroke(strokePx, cap = StrokeCap.Round)
            )

            // HANDLE (Stays locked at the starting left boundary)
            val center = Offset(size.width / 2f, size.height / 2f)
            val angle = startAngle + (totalSweep * progressFraction) // Will reduce to startAngle (-215f)

            val radius = arcSize.width / 2f
            val rad = angle * (PI.toFloat() / 180f)

            val x = cos(rad) * radius
            val y = sin(rad) * radius

            drawPoints(
                points = listOf(Offset(center.x + x, center.y + y)),
                pointMode = PointMode.Points,
                color = handleColor,
                strokeWidth = strokePx * 3f,
                cap = StrokeCap.Round
            )
        }

        // CENTER TEXT
        Text(
            text = formatSeconds((remainingTime / 1000L).toInt()).toString(),
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )


        Button(
            onClick = onToggle,
                modifier = Modifier.align(Alignment.BottomCenter),
            colors = ButtonDefaults.buttonColors(
                containerColor = when {
                    remainingTime <= 0L -> Color.Blue
                    isRunning -> Color.Red
                    else -> Color.Green
                }
            )
        ) {
            Text(
                text = when {
                    remainingTime <= 0L -> "Start"
                    isRunning -> "Pause"
                    else -> "Resume"
                }
            )
        }

        // BUTTON

    }
}