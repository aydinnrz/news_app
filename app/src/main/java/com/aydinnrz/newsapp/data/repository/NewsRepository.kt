package com.aydinnrz.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.aydinnrz.newsapp.data.network.ApiService
import com.aydinnrz.newsapp.data.paging.NewsPagingSource
import javax.inject.Inject

class NewsRepository @Inject constructor(private val api: ApiService) {

    fun searchNews(query: String) = Pager(
        // Configure how data is loaded by passing additional properties to PagingConfig
        config = PagingConfig(pageSize = 10)
    ) {
        NewsPagingSource(api, query)
    }.flow

}