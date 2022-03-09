package com.fueled.core.base

import androidx.annotation.BoolRes
import androidx.annotation.DimenRes
import androidx.annotation.StringRes

interface ResourceProvider {

    fun getString(@StringRes resId: Int): String

    fun getString(@StringRes resId: Int, vararg args: Any): String
}
