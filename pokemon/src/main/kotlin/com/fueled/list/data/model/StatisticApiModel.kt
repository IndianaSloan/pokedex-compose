package com.fueled.list.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StatisticApiModel(
    val name: String,
    val url: String,
)
