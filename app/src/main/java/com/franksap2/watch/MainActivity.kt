package com.franksap2.watch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.franksap2.watch.ui.MainScreen
import com.franksap2.watch.ui.theme.ChronometerComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChronometerComposeTheme {
                Surface(
                    Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   MainScreen()
                }
            }
        }
    }
}