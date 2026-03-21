package com.example.settings.presentation.elements

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color

@Composable
internal fun ColumnsAmountElement(number: Int, chosen: Boolean, onClick: () -> Unit) {
    val containerColor by animateColorAsState(
        targetValue = if (chosen) Color.Black else Color.White,
        animationSpec = tween(durationMillis = 400),
        label = "containerColor"
    )
    val contentColor by animateColorAsState(
        targetValue = if (chosen) Color.White else Color.Black,
        animationSpec = tween(durationMillis = 400),
        label = "contentColor"
    )

    Button(
        shape = CircleShape,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            contentColor = contentColor,
            containerColor = containerColor
        )
    ) { Text(number.toString()) }
}