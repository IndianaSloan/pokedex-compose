package com.fueled.core_ui.theme

import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val TextColor = Color(0xFF2B292C)
val Primary = Color(0xFFD43C47)
val PrimaryVariant = Color(0xFFFD9199)

val PokedexLightColors = lightColors(
    primary = Primary,
    onPrimary = Color.White,
    primaryVariant = PrimaryVariant,
    secondary = TextColor,
    secondaryVariant = TextColor,
    onSecondary = Color.White,
    onBackground = TextColor,
    onSurface = TextColor,
)

// TODO: Define Dark theme
val PokedexDarkColors = PokedexLightColors