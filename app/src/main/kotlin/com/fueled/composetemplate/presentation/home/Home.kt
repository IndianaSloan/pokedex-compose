package com.fueled.composetemplate.presentation.home

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.fueled.composetemplate.extension.currentDestinationAsState
import com.fueled.composetemplate.extension.navigateToTab
import com.fueled.composetemplate.presentation.navigation.PokedexNavigation
import com.fueled.core_ui_resources.R

@Composable
internal fun Home() {
    val navController = rememberNavController()
    val currentDestination by navController.currentDestinationAsState()
    var toolbarText by rememberSaveable { mutableStateOf("") }

    Scaffold(
        bottomBar = {
            HomeBottomNavigation(
                selectedDestination = currentDestination.destination,
                onDestinationSelected = navController::navigateToTab,
                modifier = Modifier.fillMaxWidth()
            )
        },
        topBar = {
            TopAppBar(
                navigationIcon = if (currentDestination.isTopLevel) {
                    null
                } else {
                    {
                        IconButton(onClick = navController::navigateUp) {
                            Icon(
                                painter = rememberImagePainter(R.drawable.ic_back),
                                contentDescription = null
                            )
                        }
                    }
                },
                title = {
                    Text(
                        text = toolbarText,
                        style = MaterialTheme.typography.h1,
                    )
                },
                elevation = 0.dp
            )
        }
    ) {
        PokedexNavigation(
            modifier = Modifier
                .fillMaxHeight()
                .padding(bottom = 56.dp),
            navController = navController,
            setToolbarTitle = { title -> toolbarText = title }
        )
    }
}
