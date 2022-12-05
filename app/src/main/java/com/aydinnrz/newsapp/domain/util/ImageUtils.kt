package com.aydinnrz.newsapp.domain.util

import android.widget.ImageView
import coil.ImageLoader
import coil.request.ImageRequest

object ImageUtils {

    fun ImageView.loadWithCoil(url: String?){
        val imageLoader = ImageLoader(this.context)
        val request = ImageRequest.Builder(this.context)
            .data(url)
            .target(this)
            .crossfade(250)
            .allowHardware(false)
            .build()
        imageLoader.enqueue(request)
    }
}