package com.aydinnrz.newsapp.domain.util

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient

object WebViewUtils {

    @SuppressLint("SetJavaScriptEnabled")
    fun WebView.loadWebUrl(url:String){
        this@loadWebUrl.settings.javaScriptEnabled = true
        this@loadWebUrl.webViewClient = WebViewClient()
        this@loadWebUrl.loadUrl(url)
    }
}