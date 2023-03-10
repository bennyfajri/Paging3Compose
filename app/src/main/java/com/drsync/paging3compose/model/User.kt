package com.drsync.paging3compose.model

import androidx.room.Embedded
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("links")
    @Embedded
    val userLinks: UserLinks,
    val username: String,
    @SerialName("profile_image")
    @Embedded
    val profileImage: ProfileImage
)
