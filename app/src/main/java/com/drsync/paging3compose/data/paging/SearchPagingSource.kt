package com.drsync.paging3compose.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.drsync.paging3compose.data.remote.ApiService
import com.drsync.paging3compose.model.UnsplashImage
import com.drsync.paging3compose.util.Constants.ITEM_PER_PAGE

class SearchPagingSource (
    private val apiService: ApiService,
    private val query: String
) : PagingSource<Int, UnsplashImage>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashImage> {
        val currentPage = params.key ?: 1
        return try {
            val response = apiService.searchImages(query = query, perPage = ITEM_PER_PAGE)
            val endOfPaginationReached = response.images.isEmpty()
            if (response.images.isNotEmpty()) {
                LoadResult.Page(
                    data = response.images,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (endOfPaginationReached) null else currentPage + 1
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UnsplashImage>): Int? {
        return state.anchorPosition
    }

}