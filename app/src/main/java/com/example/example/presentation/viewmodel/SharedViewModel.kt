package com.example.example.presentation.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.example.data.network.model.Quote
import com.example.example.data.network.model.Result
import com.example.example.domain.usecase.QuotesUsecase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SharedViewModel @ViewModelInject constructor(
    private val usecase: QuotesUsecase,
    // not useful for now
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val message: MutableLiveData<MessageState> by lazy {
        MutableLiveData<MessageState>()
    }

    val getMessage by lazy {
        message
    }

    private fun updateState(state: MessageState) {
        message.value = state
    }

    fun updateMessageFromApi() =
        viewModelScope.launch {
            updateState(MessageState.Loading)
            when (val result: Result<Quote> = usecase.execute()) {
                is Result.Success -> updateState(MessageState.Finish(result.data))
                is Result.Failure -> updateState(MessageState.Error(result.exception.message))
            }
        }
}

sealed class MessageState {
    object Loading : MessageState()
    data class Finish(val data: Quote) : MessageState()
    data class Error(val msg: String?) : MessageState()
}