package com.fueled.core_ui.extension

import androidx.constraintlayout.compose.ConstrainScope

fun ConstrainScope.center() {
    top.linkTo(parent.top)
    bottom.linkTo(parent.bottom)
    start.linkTo(parent.start)
    end.linkTo(parent.end)
}

fun ConstrainScope.centerBottom() {
    bottom.linkTo(parent.bottom)
    start.linkTo(parent.start)
    end.linkTo(parent.end)
}
