package com.drsync.paging3compose.data.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.drsync.paging3compose.data.local.dao.UnsplashImageDao
import com.drsync.paging3compose.data.local.dao.UnsplashRemoteKeysDao
import com.drsync.paging3compose.model.UnsplashImage
import com.drsync.paging3compose.model.UnsplashRemoteKeys

@Database(
    entities = [
        UnsplashImage::class,
        UnsplashRemoteKeys::class
    ],
    version = 1
)
abstract class UnsplashDatabase : RoomDatabase() {
    abstract fun unsplashImageDao(): UnsplashImageDao
    abstract fun unsplashRemoteKeysDao(): UnsplashRemoteKeysDao
}