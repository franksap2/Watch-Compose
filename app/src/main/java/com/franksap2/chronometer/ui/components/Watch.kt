package com.franksap2.chronometer.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Watch(
    progressProvider: () -> Long,
    modifier: Modifier = Modifier
) {
    val surfaceColor = MaterialTheme.colors.surface

    Box(modifier = modifier.fillMaxSize()) {
        FaceBackground(
            resolution = 1,
            textSize = 24.sp,
            lineSize = 10.dp,
            lineMaxSize = 20.dp,
            lineWidth = 2.dp,
            faceType = FaceType.WATCH
        )
        Canvas(
            modifier = Modifier.fillMaxSize(1f),
            onDraw = {
                val millis = progressProvider()

                drawDial(
                    value = 360f * (millis / MINUTE),
                    circleColor = surfaceColor,
                    centerDial = false,
                    dialScrewSize = 5.dp,
                    dialWidth = 3.dp
                )
            }
        )
    }
}