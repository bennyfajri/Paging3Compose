package com.drsync.paging3compose.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class Urls(
    @field:SerializedName("regular")
    val regularImage: String
)
