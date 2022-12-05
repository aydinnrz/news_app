package com.aydinnrz.newsapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.aydinnrz.newsapp.data.repository.NewsRepository
import com.aydinnrz.newsapp.domain.constants.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private var searchQuery: MutableStateFlow<String> = MutableStateFlow(Constants.DEFAULT_SEARCH_QUERY)

    // gets one page of data from paging source
    // executes when value of searchQuery changes
    @OptIn(ExperimentalCoroutinesApi::class)
    val newsFlow = searchQuery.flatMapLatest { query ->
        repository.searchNews(query)
    }.cachedIn(viewModelScope)

    // set search query when user inserts a new one
    fun setSearchQuery(query:String){
        searchQuery.value = query
    }
}