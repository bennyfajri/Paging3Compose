package com.drsync.paging3compose.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.drsync.paging3compose.util.Constants

@Entity(tableName = Constants.UNSPLASH_REMOTE_KEYS_TABLE)
data class UnsplashRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)
