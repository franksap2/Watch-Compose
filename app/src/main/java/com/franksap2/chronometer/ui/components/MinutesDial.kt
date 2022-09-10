package com.franksap2.chronometer.ui.components

import android.content.res.Configuration
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.franksap2.chronometer.ui.theme.ChronometerComposeTheme

@Composable
fun MinutesDial(modifier: Modifier = Modifier) {

    val test = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = Unit) {
        test.animateTo(360f, tween(60_000 * 30, easing = LinearEasing))
    }

    Chronometer(
        modifier = modifier,
        resolution = 2,
        textSize = 12.sp,
        lineSize = 4.dp,
        lineMaxSize = 6.dp,
        lineWidth = 1.dp,
        progressProvider = { test.value },
        dialScrewSize = 3.dp,
        centerDial = true,
        dialWidth = 2.dp
    )

}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ChronometerPreview() {
    ChronometerComposeTheme {
        MinutesDial()
    }
}