package com.fueled.list.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NamedApiModel(
    val name: String,
    val url: String
)
