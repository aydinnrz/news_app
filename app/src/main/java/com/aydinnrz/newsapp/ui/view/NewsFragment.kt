package com.aydinnrz.newsapp.ui.view


import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.aydinnrz.newsapp.R
import com.aydinnrz.newsapp.databinding.FragmentNewsBinding
import com.aydinnrz.newsapp.domain.constants.Constants
import com.aydinnrz.newsapp.domain.util.VerticalSpacingItemDecorator
import com.aydinnrz.newsapp.ui.base.BaseFragment
import com.aydinnrz.newsapp.ui.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class NewsFragment : BaseFragment<FragmentNewsBinding>(), NewsPagedAdapter.OnItemClickListener {


    private lateinit var adapter: NewsPagedAdapter
    private val viewModel: NewsViewModel by viewModels()

    override fun getViewBinding() = FragmentNewsBinding.inflate(layoutInflater)

    override fun initUi() {
        setNewsRecyclerView()
        searchByQuery()
    }

    private fun setNewsRecyclerView(){
        adapter = NewsPagedAdapter(this)
        with(binding){
            recviewNews.setHasFixedSize(true)
            recviewNews.addItemDecoration(VerticalSpacingItemDecorator(resources.getDimensionPixelSize(
                R.dimen.margin_8))
            )
            recviewNews.adapter = adapter
        }
    }

    override fun setObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.newsFlow.collectLatest { pagingData ->
                // send new paged data to adapter
                adapter.submitData(pagingData)
            }

            adapter.loadStateFlow.collectLatest { loadStates ->
                when(loadStates.refresh){
                    is LoadState.Error ->{
                        Toast.makeText(requireContext(), R.string.error_toast, Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                    }
                }
            }
        }
    }

    // send request to api based on search query
    private fun searchByQuery(){
        binding.svNews.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                // close keyboard
                binding.svNews.clearFocus()
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                // when search input is more than 1 char send it as query to api
                if (!newText.isNullOrEmpty() && newText.length > 1){
                    val searchQuery = newText.lowercase()
                    viewModel.setSearchQuery(searchQuery)
                }
                else{
                    viewModel.setSearchQuery(Constants.DEFAULT_SEARCH_QUERY)
                }
                return false
            }

        })
    }

    // on recycler view item click open webView with url passed back here
    override fun onItemClick(url: String?) {
    }

}