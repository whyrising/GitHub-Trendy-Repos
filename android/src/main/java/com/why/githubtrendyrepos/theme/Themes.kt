package com.why.githubtrendyrepos.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColors(
    primary = Grey100,
    primaryVariant = Blue900,
    onPrimary = Color.Black,
    secondary = Blue600,
    secondaryVariant = Blue900,
    onSecondary = Color.White,
//    onSurface = Color.Red,
    error = Red800
)

private val DarkColors = darkColors(
    primary = Blue300,
    primaryVariant = Blue600,
    onPrimary = Color.White,
    secondary = Blue300,
    onSecondary = Color.White,
    error = Red200
)

private fun getAppropriateColors(darkTheme: Boolean) = when {
    darkTheme -> DarkColors
    else -> LightColors
}

@Composable
fun MyTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = getAppropriateColors(isDarkTheme),
        typography = TemplateTypography,
        shapes = MaterialTheme.shapes,
        content = content
    )
}
