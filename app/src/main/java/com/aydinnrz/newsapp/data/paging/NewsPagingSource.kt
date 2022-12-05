package com.aydinnrz.newsapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aydinnrz.newsapp.data.model.Article
import com.aydinnrz.newsapp.data.network.ApiService

class NewsPagingSource(private val api: ApiService, private val query:String) : PagingSource<Int, Article>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            // params.key is the currentPage
            val pageNumber = params.key ?: 1
            val response = api.searchNews(page = pageNumber, pageSize = 10, query = query)
            val articlesList = mutableListOf<Article>()
            val data = response.body()?.articles ?: emptyList()
            articlesList.addAll(data)

            // represents one page of results in recyclerView
            LoadResult.Page(
                data = articlesList,
                prevKey = if (pageNumber == 1) null else -1,
                nextKey = if (articlesList.isNotEmpty()) pageNumber.plus(1) else null
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}