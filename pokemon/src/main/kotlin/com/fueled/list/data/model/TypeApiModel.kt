package com.fueled.list.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TypeApiModel(
    val slot: Int,
    val type: NamedApiModel,
)
