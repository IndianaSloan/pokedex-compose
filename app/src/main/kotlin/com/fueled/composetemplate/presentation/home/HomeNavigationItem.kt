package com.fueled.composetemplate.presentation.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.fueled.composetemplate.presentation.navigation.Destination

internal class HomeNavigationItem(
    val destination: Destination,
    @StringRes val labelResId: Int,
    @StringRes val contentDescriptionResId: Int,
    @DrawableRes val iconResId: Int,
)
