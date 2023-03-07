package com.drsync.paging3compose.model

import kotlinx.serialization.SerialName

data class SearchResult(
    @SerialName("results")
    val images: List<UnsplashImage>
)
