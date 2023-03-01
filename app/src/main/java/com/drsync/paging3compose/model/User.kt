package com.drsync.paging3compose.model

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @field:SerializedName("links")
    @Embedded
    val userLinks: UserLinks,

    @field:SerializedName("username")
    val username: String
)
