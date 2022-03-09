package com.fueled.core_ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun PokedexTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        PokedexDarkColors
    } else {
        PokedexLightColors
    }

    MaterialTheme(
        colors = colors,
        typography = PokedexTypography,
        shapes = PokedexShapes,
        content = content
    )
}
