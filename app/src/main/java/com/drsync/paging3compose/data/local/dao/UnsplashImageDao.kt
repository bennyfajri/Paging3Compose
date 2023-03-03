package com.drsync.paging3compose.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.drsync.paging3compose.model.UnsplashImage
import com.drsync.paging3compose.util.Constants.UNSPLASH_IMAGE_TABLE

@Dao
interface UnsplashImageDao {

    @Query("SELECT * FROM $UNSPLASH_IMAGE_TABLE")
    fun getAllImages(): PagingSource<Int, UnsplashImage>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addImages(image: List<UnsplashImage>)

    @Query("DELETE FROM $UNSPLASH_IMAGE_TABLE")
    suspend fun deleteAllImages()

}