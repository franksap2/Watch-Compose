package com.franksap2.chronometer.ui.components

import android.content.res.Configuration
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.franksap2.chronometer.ui.theme.Amber700
import com.franksap2.chronometer.ui.theme.ChronometerComposeTheme

@Composable
fun Chronometer() {

    val surfaceColor = MaterialTheme.colors.surface

    val test = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = Unit) {
        test.animateTo(360f, tween(60_000, easing = LinearEasing))
    }

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(20.dp)
    ) {
        ChronometerBackground()
        Canvas(
            modifier = Modifier.fillMaxSize(1f),
            onDraw = {
                drawDial(test.value, surfaceColor)
            },
        )
    }

}

fun DrawScope.drawDial(value: Float, circleColor: Color) {
    rotate(value) {
        drawLine(
            color = Amber700.copy(0.8f),
            start = size.center.copy(y = size.height * 0.6f),
            end = Offset(size.width / 2, size.height * 0.1f),
            strokeWidth = 3.dp.toPx(),
            cap = StrokeCap.Round
        )

        drawCircle(circleColor, 5.dp.toPx(), size.center)
        drawCircle(Amber700, 5.dp.toPx(), size.center, style = Stroke(width = 2.dp.toPx()))
    }

}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ChronometerPreview() {
    ChronometerComposeTheme {
        Chronometer()
    }
}