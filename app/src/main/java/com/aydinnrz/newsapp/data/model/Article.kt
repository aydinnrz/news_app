package com.aydinnrz.newsapp.data.model

data class Article(
    val source:NewsSource?,
    val author:String?,
    val title:String?,
    val description:String?,
    val url:String?,
    val urlToImage:String?,
    val publishedAt:String?,
    val content:String?
)
