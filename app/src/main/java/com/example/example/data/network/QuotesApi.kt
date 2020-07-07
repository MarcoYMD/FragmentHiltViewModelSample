package com.example.example.data.network

import com.example.example.data.network.model.QuoteResponse
import retrofit2.http.GET


interface QuotesApi {
    @GET("qod")
    suspend fun getQuoteOfDay(): QuoteResponse
}