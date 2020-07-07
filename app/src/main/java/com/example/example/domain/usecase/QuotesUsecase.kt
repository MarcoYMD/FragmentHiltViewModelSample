package com.example.example.domain.usecase

import com.example.example.data.network.model.Result
import com.example.example.data.repository.QuotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuotesUsecase @Inject constructor(
    private val repository: QuotesRepository
) {
    suspend fun execute() = withContext(Dispatchers.IO) {
        try {
            Result.Success(repository.getQuoteOfDay())
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }
    }
}