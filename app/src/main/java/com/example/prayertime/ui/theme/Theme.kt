package com.example.prayertime.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColors = darkColorScheme(
    primary = SunriseGold,
    secondary = SoftBlue,
    background = NightSky,
    surface = NightSky
)

private val LightColors = lightColorScheme(
    primary = SunriseGold,
    secondary = SoftBlue,
    background = NightSky,
    surface = NightSky
)

@Composable
fun PrayerTimesTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography,
        content = content
    )
}
