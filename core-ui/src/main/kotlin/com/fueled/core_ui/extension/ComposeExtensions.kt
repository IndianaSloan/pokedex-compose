package com.fueled.core_ui.extension

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@SuppressLint("ComposableModifierFactory")
@Composable
fun Modifier.clickable(onClick: (() -> Unit)? = null): Modifier {
    return onClick?.let { block ->
        this.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(),
            onClick = block
        )
    } ?: this
}
