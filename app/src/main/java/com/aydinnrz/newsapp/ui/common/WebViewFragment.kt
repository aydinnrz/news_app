package com.aydinnrz.newsapp.ui.common


import androidx.navigation.fragment.navArgs
import com.aydinnrz.newsapp.databinding.FragmentWebViewBinding
import com.aydinnrz.newsapp.domain.util.WebViewUtils.loadWebUrl
import com.aydinnrz.newsapp.ui.base.BaseFragment

class WebViewFragment : BaseFragment<FragmentWebViewBinding>() {

    override fun getViewBinding() = FragmentWebViewBinding.inflate(layoutInflater)

    private val args: WebViewFragmentArgs by navArgs()

    override fun initUi() {
        binding.webview.loadWebUrl(args.url)
    }
}