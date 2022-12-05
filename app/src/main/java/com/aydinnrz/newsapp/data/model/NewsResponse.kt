package com.aydinnrz.newsapp.data.model

data class NewsResponse(
    // status takes two values -> ok and error. In the case of error a code and message property will be sent
    val status:String?,
    val totalResults: Int?,
    val articles: List<Article>?,
    // if there is an error code and message values will be sent from api
    val code:String?,
    val message:String?
)
