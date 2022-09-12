package com.franksap2.chronometer.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.franksap2.chronometer.ui.theme.ChronometerComposeTheme

const val MINUTE = 60_000f

@Composable
fun SecondsDial(progressProvider: () -> Long, modifier: Modifier = Modifier) {


    Box(modifier = modifier) {

        Chronometer(
            resolution = 2,
            textSize = 12.sp,
            lineSize = 4.dp,
            lineMaxSize = 6.dp,
            lineWidth = 1.dp,
            progressProvider = { 360f * (progressProvider() / MINUTE) },
            dialScrewSize = 3.dp,
            centerDial = true,
            dialWidth = 2.dp,
            faceType = FaceType.SECONDS
        )
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ChronometerPreview() {
    ChronometerComposeTheme {
        SecondsDial(progressProvider = { 30_000L })
    }
}