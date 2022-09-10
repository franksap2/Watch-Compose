package com.franksap2.chronometer.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.franksap2.chronometer.ui.components.MinutesDial
import com.franksap2.chronometer.ui.components.SecondsDial
import com.franksap2.chronometer.ui.theme.ChronometerComposeTheme

@Composable
fun MainScreen() {
    Column() {

        Box(
            modifier = Modifier
                .padding(20.dp)
                .shadow(4.dp, CircleShape)
                .background(MaterialTheme.colors.surface, CircleShape)
                .aspectRatio(1f)
        ) {
            MinutesDial(
                Modifier
                    .padding(top = 65.dp)
                    .size(90.dp)
                    .align(Alignment.TopCenter)
            )
            SecondsDial()

        }
    }

}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MainScreenPreview() {
    ChronometerComposeTheme {
        MainScreen()
    }
}