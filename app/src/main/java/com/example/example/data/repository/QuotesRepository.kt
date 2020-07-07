package com.example.example.data.repository

import com.example.example.data.network.QuotesApi
import com.example.example.data.network.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuotesRepository @Inject constructor(
    private val api: QuotesApi
) {
    suspend fun getQuoteOfDay() = withContext(Dispatchers.IO) {
        api.getQuoteOfDay().contents.quotes[0]
    }
}