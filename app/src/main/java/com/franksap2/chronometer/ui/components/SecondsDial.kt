package com.franksap2.chronometer.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.franksap2.chronometer.ui.theme.ChronometerComposeTheme
import com.franksap2.chronometer.ui.utils.formatTimer

private const val MINUTE = 60_000f

@Composable
fun SecondsDial(progressProvider: () -> Long) {


    Box {

        Chronometer(
            resolution = 1,
            textSize = 24.sp,
            lineSize = 10.dp,
            lineMaxSize = 20.dp,
            lineWidth = 2.dp,
            progressProvider = { 360f * (progressProvider() / MINUTE) }
        )

        Text(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 100.dp),
            text = progressProvider().formatTimer()
        )
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ChronometerPreview() {
    ChronometerComposeTheme {
        SecondsDial { 30_000L }
    }
}