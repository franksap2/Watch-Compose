package com.franksap2.chronometer.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.franksap2.chronometer.ui.theme.ChronometerComposeTheme
import com.franksap2.chronometer.ui.utils.HALF_HOUR_MILLI
import com.franksap2.chronometer.ui.utils.MAX_ROTATION

@Composable
fun MinutesDial(modifier: Modifier = Modifier, progressProvider: () -> Long) {

    Chronometer(
        modifier = modifier,
        resolution = 2,
        textSize = 12.sp,
        lineSize = 4.dp,
        lineMaxSize = 6.dp,
        lineWidth = 1.dp,
        progressProvider = { MAX_ROTATION * (progressProvider() / HALF_HOUR_MILLI) },
        dialScrewSize = 3.dp,
        centerDial = true,
        dialWidth = 2.dp,
        faceType = FaceType.MINUTES
    )

}


@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun ChronometerPreview() {
    ChronometerComposeTheme {
        MinutesDial { 60_000 }
    }
}