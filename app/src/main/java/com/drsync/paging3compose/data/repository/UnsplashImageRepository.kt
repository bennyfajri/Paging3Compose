package com.drsync.paging3compose.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.drsync.paging3compose.data.local.UnsplashDatabase
import com.drsync.paging3compose.data.paging.SearchPagingSource
import com.drsync.paging3compose.data.paging.UnsplashRemoteMediator
import com.drsync.paging3compose.data.remote.ApiService
import com.drsync.paging3compose.model.UnsplashImage
import com.drsync.paging3compose.util.Constants.ITEM_PER_PAGE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalPagingApi
class UnsplashImageRepository @Inject constructor(
    private val apiService: ApiService,
    private val database: UnsplashDatabase
) {

    fun getAllImages(): Flow<PagingData<UnsplashImage>> {
        val pagingSourceFactory = { database.unsplashImageDao().getAllImages() }
        return Pager(
            config = PagingConfig(pageSize = ITEM_PER_PAGE),
            remoteMediator = UnsplashRemoteMediator(
                apiService = apiService,
                database = database
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    fun searchImages(query: String): Flow<PagingData<UnsplashImage>> {
        return Pager(
            config = PagingConfig(pageSize = ITEM_PER_PAGE),
            pagingSourceFactory = {
                SearchPagingSource(apiService = apiService, query = query)
            }
        ).flow
    }

}