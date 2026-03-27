package com.myapp.dailynote.presentation.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.myapp.dailynote.R

@Composable
fun ActionsRow(
    actionIconSize: Dp,
    progress: Float,
    onDelete: () -> Unit,
    onEdit: () -> Unit,
    onFavorite: () -> Unit
) {

    val animationScale by animateFloatAsState(targetValue = progress)
    val animationAlpha by animateFloatAsState(targetValue = progress)


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(horizontal = 12.dp)
            .graphicsLayer(
                scaleX = animationScale,
                scaleY = animationScale,
                alpha = animationAlpha
            ),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ActionItem(
            color = Color(0xFF75B2FA),
            icon = painterResource(R.drawable.ic_star),
            size = actionIconSize,
            onClick = onDelete
        )
        Spacer(Modifier.width(8.dp))
        ActionItem(
            color = Color(0xFF0978F6),
            icon = painterResource(R.drawable.ic_date_range),
            size = actionIconSize,
            onClick = onDelete
        )
        Spacer(Modifier.width(8.dp))
        ActionItem(
            color = Color(0xFF0978F6),
            icon = painterResource(R.drawable.ic_sharp_timer),
            size = actionIconSize,
            onClick = onEdit
        )
        Spacer(Modifier.width(8.dp))
        ActionItem(
            color = Color.Red,
            icon = painterResource(R.drawable.ic_delete),
            size = actionIconSize,
            onClick = onFavorite
        )
    }
}


@Composable
fun ActionItem(
    color: Color,
    icon: Painter,
    size: Dp,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .width(size)
            .height(50.dp)
            .clip(shape = RoundedCornerShape(50.dp))
            .background(color)
            .clickable{
                onClick()
            },
        contentAlignment = Alignment.Center
    ){
        Icon(
            painter = icon,
            contentDescription = null,
            tint = Color.White
        )
    }
}