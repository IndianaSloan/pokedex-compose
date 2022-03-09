package com.fueled.composetemplate.presentation.home

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.fueled.composetemplate.presentation.navigation.Destination
import com.fueled.core_ui.theme.Primary
import com.fueled.core_ui.theme.TextColor
import com.fueled.core_ui_resources.R

@Composable
internal fun HomeBottomNavigation(
    selectedDestination: Destination,
    onDestinationSelected: (Destination) -> Unit,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = contentColorFor(backgroundColor = MaterialTheme.colors.surface),
    ) {
        HomeNavigationItems.forEach { item ->
            val isSelected = selectedDestination == item.destination
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconResId),
                        contentDescription = stringResource(item.contentDescriptionResId),
                    )
                },
                label = { Text(text = stringResource(id = item.labelResId)) },
                selected = isSelected,
                onClick = { onDestinationSelected(item.destination) },
                selectedContentColor = Primary,
                unselectedContentColor = TextColor
            )
        }
    }
}

private val HomeNavigationItems = listOf(
    HomeNavigationItem(
        Destination.Pokemon,
        R.string.destination_pokemon,
        R.string.content_desc_pokemon,
        R.drawable.ic_menu
    ),
    HomeNavigationItem(
        Destination.Types,
        R.string.destination_types,
        R.string.content_desc_types,
        R.drawable.ic_pool
    )
)
