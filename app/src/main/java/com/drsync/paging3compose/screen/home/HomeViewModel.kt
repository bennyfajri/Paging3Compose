package com.drsync.paging3compose.screen.home

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import com.drsync.paging3compose.data.repository.UnsplashImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: UnsplashImageRepository
) : ViewModel() {
    val getAllImages = repository.getAllImages()
}