package com.franksap2.chronometer.ui.components

import android.content.res.Configuration
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.franksap2.chronometer.ui.theme.ChronometerComposeTheme
import com.franksap2.chronometer.ui.utils.formatTimer


@Composable
fun SecondsDial() {

    val test = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = Unit) {
        test.animateTo(360f, tween(60_000, easing = LinearEasing))
    }

    Box {

        Chronometer(
            resolution = 1,
            textSize = 24.sp,
            lineSize = 10.dp,
            lineMaxSize = 20.dp,
            lineWidth = 2.dp,
            progressProvider = { test.value }
        )

        val testValue = 60_000 * test.value / 360f
        Text(
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 100.dp),
            text = testValue.toLong().formatTimer()
        )
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ChronometerPreview() {
    ChronometerComposeTheme {
        SecondsDial()
    }
}