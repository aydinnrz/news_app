package com.aydinnrz.newsapp.ui.view


import com.aydinnrz.newsapp.databinding.FragmentNewsBinding
import com.aydinnrz.newsapp.ui.base.BaseFragment


class NewsFragment : BaseFragment<FragmentNewsBinding>() {

    override fun getViewBinding() = FragmentNewsBinding.inflate(layoutInflater)

}