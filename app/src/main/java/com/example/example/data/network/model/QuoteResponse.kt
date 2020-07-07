package com.example.example.data.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuoteResponse(
    val success: Success,
    val contents: Content
)

@JsonClass(generateAdapter = true)
data class Success(
    val total: Int
)

@JsonClass(generateAdapter = true)
data class Content(
    val quotes: List<Quote>
)

@JsonClass(generateAdapter = true)
data class Quote(
    val title: String,
    val author: String,
    val quote: String,
    val background: String?,
    val length: Int
)