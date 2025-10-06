package com.jahez.jahezchat.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jahez.jahezchat.mapper.toUIMessage
import com.jahez.jahezchat.presentation.model.ChatUIMessage
import com.jahez.jahezchat.mapper.toWebMessage
import com.jahez.jahezchat.presentation.ui.ChatUIState
import com.jahez.storage.domain.ObserveSavedMessagesUseCase
import com.jahez.websocket.domain.AppThrowable
import com.jahez.websocket.domain.CloseConnectionUseCase
import com.jahez.websocket.domain.ConnectUseCase
import com.jahez.websocket.domain.ObserveExceptionsUseCase
import com.jahez.websocket.domain.ObserveMessagesUseCase
import com.jahez.websocket.domain.SendMessageUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val connectUseCase: ConnectUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val observeMessagesUseCase: ObserveMessagesUseCase,
    private val observeSavedMessagesUseCase: ObserveSavedMessagesUseCase,
    private val observeExceptionsUseCase: ObserveExceptionsUseCase,
    private val closeConnectionUseCase: CloseConnectionUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChatUIState())
    val uiState: StateFlow<ChatUIState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            observeMessagesUseCase().collect { text ->
                _uiState.update { state ->
                    state.copy(
                        data = state.data + text.toUIMessage()
                    )
                }
            }
        }

        viewModelScope.launch {
            observeExceptionsUseCase().collect { t ->
                _uiState.update { state ->
                    state.copy(
                        error = t
                    )
                }
            }
        }
    }

    fun connect() {
        connectUseCase()
    }

    fun send(message: ChatUIMessage) {
        viewModelScope.launch {
            val ok = sendMessageUseCase(message.toWebMessage())
            if (ok) {
                _uiState.update { state ->
                    state.copy(
                        data = state.data + message
                    )
                }
            } else {
                _uiState.update { state ->
                    state.copy(
                        error = AppThrowable.MESSAGE_NOT_SENT_ERROR
                    )
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        closeConnectionUseCase()
    }

    fun getSavedMessages() {
        viewModelScope.launch {
            observeSavedMessagesUseCase().collect { entities ->
                val msgs = entities.map { entity ->
                    ChatUIMessage(
                        message = entity.text,
                        time = entity.time,
                        isMine = entity.isMine,
                        avatarUrl = entity.avatarUrl
                    )
                }
                _uiState.update { state ->
                    state.copy(
                        data = msgs
                    )
                }
            }
        }
    }

    fun resetError() {
        _uiState.update { state ->
            state.copy(
                error = null
            )
        }
    }
}
