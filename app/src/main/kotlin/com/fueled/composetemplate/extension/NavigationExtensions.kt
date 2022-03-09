package com.fueled.composetemplate.extension

import androidx.annotation.StringRes
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.fueled.composetemplate.presentation.navigation.Destination
import com.fueled.composetemplate.presentation.navigation.Screen
import com.fueled.core_ui_resources.R

internal fun NavController.navigateToTab(destination: Destination) {
    navigate(destination.route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

@Composable
internal fun NavController.currentDestinationAsState(): State<DestinationState> {
    val state = remember {
        mutableStateOf(DestinationState(Destination.Pokemon, true, R.string.app_name))
    }
    DisposableEffect(this) {
        val listener = NavController.OnDestinationChangedListener { _, dest, _ ->
            val topLevelDestination = when {
                dest.hierarchy.any { it.route == Destination.Pokemon.route } -> {
                    Destination.Pokemon
                }
                dest.hierarchy.any { it.route == Destination.Types.route } -> {
                    Destination.Types
                }
                else -> return@OnDestinationChangedListener
            }

            val screenTitleRes = Screen.getScreenTitleForRoute(dest.route, topLevelDestination)
            val isTopLevel = Screen.isTopLevelDest(dest.route)
            state.value = DestinationState(topLevelDestination, isTopLevel, screenTitleRes)
        }
        addOnDestinationChangedListener(listener)
        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }
    return state
}

data class DestinationState(
    val destination: Destination,
    val isTopLevel: Boolean,
    @StringRes val screenTitleRes: Int
)
